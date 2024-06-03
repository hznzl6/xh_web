package com.example.mass.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "user_apply_activity")
public class UApplyActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer target;
    private String mass;
    private String sno;
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime apply_time;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime operate_time;
}
