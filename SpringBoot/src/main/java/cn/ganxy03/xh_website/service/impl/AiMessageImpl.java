package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.AiConversation;
import cn.ganxy03.xh_website.antity.AiMessage;
import cn.ganxy03.xh_website.repository.AiConversationRepository;
import cn.ganxy03.xh_website.repository.AiMessageRepository;
import cn.ganxy03.xh_website.service.AiMessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiMessageImpl implements AiMessageService {
    private final AiMessageRepository aiMessageRepository;

    public AiMessageImpl(AiMessageRepository aiMessageRepository) {
        this.aiMessageRepository = aiMessageRepository;
    }

    public List<Object> GetAllMessage() {
        List<AiMessage> aiMessages = aiMessageRepository.findAll();
        List<Object> formattedMessages = new ArrayList<>();
        for (AiMessage aiMessage : aiMessages) {
            formattedMessages.add(createUserMessage(aiMessage.getQuestion()));
            formattedMessages.add(createAiMessage(aiMessage.getAnswer()));
        }
        return formattedMessages;
    }



    @Override
    public List<Object> GetByUUID(String uuid) {
        List<AiMessage> aiMessages = aiMessageRepository.findByUuid(uuid);
        List<Object> formattedMessages = new ArrayList<>();
        for (AiMessage aiMessage : aiMessages) {
            formattedMessages.add(createUserMessage(aiMessage.getQuestion()));
            formattedMessages.add(createAiMessage(aiMessage.getAnswer()));
        }
        return formattedMessages;
    }

    private Map<String, String> createUserMessage(String question) {
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("message", question);
        return userMessage;
    }
    private Map<String, String> createAiMessage(String answer) {
        Map<String, String> aiMessage = new HashMap<>();
        aiMessage.put("role", "ai");
        aiMessage.put("message", answer);
        return aiMessage;
    }

}
