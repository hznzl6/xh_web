package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.AiConversation;
import cn.ganxy03.xh_website.antity.AiMessage;
import cn.ganxy03.xh_website.component.XfXhStreamClient;
import cn.ganxy03.xh_website.config.XfXhConfig;
import cn.ganxy03.xh_website.dto.MsgDTO;
import cn.ganxy03.xh_website.listener.XfXhWebSocketListener;
import cn.ganxy03.xh_website.repository.AiConversationRepository;
import cn.ganxy03.xh_website.repository.AiMessageRepository;
import cn.ganxy03.xh_website.service.AiConversationService;
import cn.ganxy03.xh_website.service.AiMessageService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.WebSocket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ai-message")
@Slf4j
public class AiMessageController {
    private final AiMessageService aiMessageService;
    private final AiConversationService aiConversationService;
    private AiMessageRepository aiMessageRepository;
    private AiConversationRepository aiConversationRepository;
    public AiMessageController(AiMessageService aiMessageService, AiMessageRepository aiMessageRepository, AiConversationService aiConversationService, AiConversationRepository aiConversationRepository) {
        this.aiMessageService = aiMessageService;
        this.aiMessageRepository = aiMessageRepository;
        this.aiConversationRepository = aiConversationRepository;
        this.aiConversationService = aiConversationService;
    }

    @Resource
    private XfXhStreamClient xfXhStreamClient;

    @Resource
    private XfXhConfig xfXhConfig;

//    @GetMapping("/ai-record")
    @GetMapping(value = "/sendQuestion", produces = "text/plain;charset=UTF-8")
    public String RecordMessage(@RequestParam String question, @RequestParam String use_code, @RequestParam String uuid, @RequestParam String initiator) {
        // 如果是无效字符串，则不对大模型进行请求
        if (StrUtil.isBlank(question)) {
            return "无效问题，请重新输入";
        }
        // 获取连接令牌
        if (!xfXhStreamClient.operateToken(XfXhStreamClient.GET_TOKEN_STATUS)) {
            return "当前大模型连接数过多，请稍后再试";
        }
        // 创建消息对象
        MsgDTO msgDTO = MsgDTO.createUserMsg(question);
        // 创建监听器
        XfXhWebSocketListener listener = new XfXhWebSocketListener(aiMessageRepository, aiConversationRepository, question, use_code, uuid, initiator);
        // 发送问题给大模型，生成 websocket 连接
        WebSocket webSocket = xfXhStreamClient.sendMsg(UUID.randomUUID().toString().substring(0, 10), Collections.singletonList(msgDTO), listener);
        if (webSocket == null) {
            // 归还令牌
            xfXhStreamClient.operateToken(XfXhStreamClient.BACK_TOKEN_STATUS);
            return "系统内部错误，请联系管理员";
        }
        try {
            int count = 0;
            // 为了避免死循环，设置循环次数来定义超时时长
            int maxCount = xfXhConfig.getMaxResponseTime() * 5;
            while (count <= maxCount) {
                Thread.sleep(200);
                if (listener.isWsCloseFlag()) {
                    break;
                }
                count++;
            }
            if (count > maxCount) {
                return "大模型响应超时，请联系管理员";
            }
            // 响应大模型的答案
            return listener.getAnswer().toString();
        } catch (InterruptedException e) {
            log.error("错误：" + e.getMessage());
            return "系统内部错误，请联系管理员";
        } finally {
            // 关闭 websocket 连接
            webSocket.close(1000, "");
            // 归还令牌
            xfXhStreamClient.operateToken(XfXhStreamClient.BACK_TOKEN_STATUS);
        }

    }

    @PostMapping("getAllMessage")
    public ResponseEntity<List> GetAllMessage() {
        List<Object> aiMessages = aiMessageService.GetAllMessage();
        return new ResponseEntity<>(aiMessages, HttpStatus.OK);
    }

    @PostMapping("getByUUID")
    public ResponseEntity<List> GetByUUID(@RequestParam String uuid) {
        List<Object> aiMessages = aiMessageService.GetByUUID(uuid);
        return new ResponseEntity<>(aiMessages, HttpStatus.OK);
    }

    @PostMapping("getHistoryByUseCode")
    public ResponseEntity<List> GetHistoryByUseCode(@RequestParam String useCode) {
        List<AiConversation> aiConversations = aiConversationService.GetHistoryByUseCode(useCode);
        return new ResponseEntity<>(aiConversations, HttpStatus.OK);
    }
}
