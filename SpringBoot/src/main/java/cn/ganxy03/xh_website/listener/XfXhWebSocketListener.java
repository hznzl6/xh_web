package cn.ganxy03.xh_website.listener;

import cn.ganxy03.xh_website.antity.AiConversation;
import cn.ganxy03.xh_website.antity.AiMessage;
import cn.ganxy03.xh_website.dto.MsgDTO;
import cn.ganxy03.xh_website.dto.ResponseDTO;
import cn.ganxy03.xh_website.repository.AiConversationRepository;
import cn.ganxy03.xh_website.repository.AiMessageRepository;
import com.alibaba.fastjson.JSONObject;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.time.LocalDateTime;

@Slf4j
public class XfXhWebSocketListener extends WebSocketListener {
    private final AiMessageRepository aiMessageRepository;
    private final AiConversationRepository aiConversationRepository;
    private int totalTokens = 0;
    private String question;
    private String use_code;
    private String uuid;
    private String initiator;
    public XfXhWebSocketListener(AiMessageRepository aiMessageRepository, AiConversationRepository aiConversationRepository, String question, String use_code, String uuid, String initiator) {
        this.aiMessageRepository = aiMessageRepository;
        this.aiConversationRepository = aiConversationRepository;
        this.question = question;
        this.use_code = use_code;
        this.uuid = uuid;
        this.initiator = initiator;
    }


    private StringBuilder answer = new StringBuilder();

    private boolean wsCloseFlag = false;

    public StringBuilder getAnswer() {
        return answer;
    }

    public boolean isWsCloseFlag() {
        return wsCloseFlag;
    }

    public int getTotalTokens() {
        return totalTokens;
    }
    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        super.onMessage(webSocket, text);
        // 将大模型回复的 JSON 文本转为 ResponseDTO 对象
        ResponseDTO responseData = JSONObject.parseObject(text, ResponseDTO.class);
        // 如果响应数据中的 header 的 code 值不为 0，则表示响应错误
        if (responseData.getHeader().getCode() != 0) {
            // 日志记录
            log.error("发生错误，错误码为：" + responseData.getHeader().getCode() + "; " + "信息：" + responseData.getHeader().getMessage());
            // 设置回答
            this.answer = new StringBuilder("大模型响应错误，请稍后再试");
            // 关闭连接标识
            wsCloseFlag = true;
            return;
        }
        // 将回答进行拼接
        for (MsgDTO msgDTO : responseData.getPayload().getChoices().getText()) {
            this.answer.append(msgDTO.getContent());
            System.out.println(text);
        }
        // 对最后一个文本结果进行处理
        if (2 == responseData.getHeader().getStatus()) {
            wsCloseFlag = true;

//            耗费的token总数
            totalTokens = responseData.getPayload().getUsage().getText().getTotalTokens();
            System.out.println(totalTokens);
            System.out.println(uuid);
            System.out.println(this.answer.toString());
            // 保存用户问题和AI回答到数据库中
            AiMessage aiMessage = new AiMessage();
            aiMessage.setUuid(uuid);
            aiMessage.setQuestion(question);
            aiMessage.setAnswer(this.answer.toString());
            aiMessage.setToken(String.valueOf(totalTokens));
            aiMessage.setQuestion_time(LocalDateTime.now());
            aiMessageRepository.save(aiMessage);

            AiConversation aiConversation =aiConversationRepository.findByUuid(uuid);
            if(aiConversation == null) {
                AiConversation aiConversation1 = new AiConversation();
                aiConversation1.setUsecode(use_code);
                aiConversation1.setUuid(uuid);
                aiConversation1.setInitiator(initiator);
                aiConversationRepository.save(aiConversation1);
            }

        }
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);

        log.error("WebSocket连接失败", t);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
    }
}
