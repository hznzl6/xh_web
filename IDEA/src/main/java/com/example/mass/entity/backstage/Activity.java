package com.example.mass.entity.backstage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "houtai_activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mass;
    private String title;
    private String desc;
    private String charge;
    private String expenditure;
    private String start_time;
    private String end_time;
    private String mark;

    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime create_time;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime update_time;
}
