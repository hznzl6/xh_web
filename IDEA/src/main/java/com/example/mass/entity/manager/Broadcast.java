package com.example.mass.entity.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "manager_broadcast")
public class Broadcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mass;
    private String title;
    private String sno;
    private String content;
    private Integer hits;

    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime create_time;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime update_time;
}
