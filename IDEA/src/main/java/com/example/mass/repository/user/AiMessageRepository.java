package com.example.mass.repository.user;


import com.example.mass.entity.user.AiMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AiMessageRepository extends JpaRepository<AiMessage, Integer> {
    List<AiMessage> getAllByUuid(String uuid);


}
