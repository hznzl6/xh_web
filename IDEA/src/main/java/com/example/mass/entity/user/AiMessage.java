package com.example.mass.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "useri_ai_message")
public class AiMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String question;
    private String answer;
    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime time;
}
