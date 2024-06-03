package com.example.mass.entity.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "manager_applyCredit")
public class ApplyCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String semester;
    private String mass;
    private String list;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM:dd hh:MM:ss")
    private LocalDateTime apply_time;
    @JsonFormat(pattern = "yyyy-MM:dd hh:MM:ss")
    private LocalDateTime update_time;

}
