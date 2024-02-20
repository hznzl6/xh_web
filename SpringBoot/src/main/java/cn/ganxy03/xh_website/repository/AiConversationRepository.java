package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.AiConversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AiConversationRepository extends JpaRepository<AiConversation, Integer> {
    AiConversation findByUuid(String uuid);

    List<AiConversation> findAllByUsecode(String useCode);

}
