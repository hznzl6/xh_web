package com.example.mass.entity.backstage;

import lombok.Data;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.*;

@Data
@Entity
@Table(name = "houtai_question_bank")
public class QuestionBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String stem;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;
    private String description;

}
