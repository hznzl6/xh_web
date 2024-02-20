package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.AiConversation;
import cn.ganxy03.xh_website.repository.AiConversationRepository;
import cn.ganxy03.xh_website.service.AiConversationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiConversationImpl implements AiConversationService {
    private final AiConversationRepository aiConversationRepository;

    public AiConversationImpl(AiConversationRepository aiConversationRepository) {
        this.aiConversationRepository = aiConversationRepository;
    }

    @Override
    public List<AiConversation> GetHistoryByUseCode(String useCode) {
        List<AiConversation> aiConversations = aiConversationRepository.findAllByUsecode(useCode);
        return aiConversations;
    }
}
