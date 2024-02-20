package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.AiConversation;

import java.util.List;

public interface AiConversationService {

    List<AiConversation> GetHistoryByUseCode(String useCode);
}
