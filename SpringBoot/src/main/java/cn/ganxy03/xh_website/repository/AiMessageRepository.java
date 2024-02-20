package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.AiMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AiMessageRepository extends JpaRepository<AiMessage, Integer> {
    List<AiMessage> findAll();

    List<AiMessage> findByUuid(String uuid);
}
