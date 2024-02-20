//package cn.ganxy03.xh_website.service.impl;
//
//import cn.ganxy03.xh_website.antity.AiHistory;
//import cn.ganxy03.xh_website.config.AiWebSocket;
//import cn.ganxy03.xh_website.repository.AiHistoryRepository;
//import cn.ganxy03.xh_website.service.AiHistoryService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import okhttp3.HttpUrl;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@Service
//public class AiHistoryImpl implements AiHistoryService {
//    private final AiHistoryRepository aiHistoryRepository;
//    private final AiWebSocket aiWebSocket;
//    public AiHistoryImpl(AiHistoryRepository aiHistoryRepository, AiWebSocket aiWebSocket) {
//        this.aiHistoryRepository = aiHistoryRepository;
//        this.aiWebSocket = aiWebSocket;
//    }
//
//    @Override
//    public AiHistory askQuestion(String question) {
//        AiHistory aiHistory = new AiHistory();
//        // 发送问题
//        String requestJson = buildRequestJson(question);
//        aiWebSocket.sendMessage(requestJson);
//
//        // 接收结果
//        String responseJson = aiWebSocket.receiveMessage();
//        System.out.println(parseResponseJson(responseJson));
//        // 存储历史记录
//        aiHistory.setQuestion(question);
//        aiHistory.setQuestion_time(LocalDateTime.now());
//        aiHistoryRepository.save(aiHistory);
//        return aiHistory;
//    }
//
//    private String buildRequestJson(String question) {
//        // 构建请求JSON
//        Map<String, Object> requestMap = new HashMap<>();
//        requestMap.put("question", question);
//        // 将Map转为JSON字符串
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.writeValueAsString(requestMap);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//
//    private String parseResponseJson(String responseJson) {
//        // 解析响应JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            Map<String, Object> responseMap = objectMapper.readValue(responseJson, new HashMap<String, Object>().getClass());
//            return responseMap.get("result").toString();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//}
