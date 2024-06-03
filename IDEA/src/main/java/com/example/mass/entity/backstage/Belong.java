package com.example.mass.entity.backstage;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "houtai_belong")
public class Belong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String period;
    private String belong;
    private Integer status;
}
