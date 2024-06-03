package com.example.mass.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_secure")
public class USecure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sno;
    private String ip;
    private Double latitude;
    private Double longitude;
    private String nation;
    private String province;
    private String city;
    private String district;

    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime time;

}
