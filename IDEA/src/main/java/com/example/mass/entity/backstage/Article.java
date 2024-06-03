package com.example.mass.entity.backstage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "houtai_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mass;
    private String title;
    private String content;
    private String poster;
    private Integer hits;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime post_time;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime update_time;
}
