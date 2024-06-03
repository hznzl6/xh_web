package com.example.mass.entity.backstage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "houtai_ai_message")
public class AIMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String question;
    private String answer;
    private Integer token;

    @JsonFormat(pattern = "yyyy-MM:dd hh:MM:ss")
    private LocalDateTime time;
}
