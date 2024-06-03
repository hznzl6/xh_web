package com.example.mass.xunfei;


import com.alibaba.fastjson.JSONObject;
import com.example.mass.entity.user.AiMessage;
import com.example.mass.repository.user.AiMessageRepository;
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
    private int totalTokens = 0;
    private String question;
    private String uuid;
    public XfXhWebSocketListener(AiMessageRepository aiMessageRepository, String question, String uuid) {
        this.aiMessageRepository = aiMessageRepository;
        this.question = question;
        this.uuid = uuid;
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
            aiMessage.setTime(LocalDateTime.now());
            aiMessageRepository.save(aiMessage);
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
