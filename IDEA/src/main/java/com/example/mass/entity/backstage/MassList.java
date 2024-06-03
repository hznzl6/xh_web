package com.example.mass.entity.backstage;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "houtai_list")
public class MassList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mass;
    private String manager;
    private String slogan;
    private String image;

    private String teacher;
    private String info;
}
