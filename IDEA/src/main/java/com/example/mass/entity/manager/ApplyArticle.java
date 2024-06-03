package com.example.mass.entity.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "manager_apply_article")
public class ApplyArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mass;
    private String title;
    private String sno;
    private String content;
    private Integer hits;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime post_time;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime update_time;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime operate_time;
}
