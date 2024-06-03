package com.example.mass.entity.manager;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manager_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mass;
    private String depart;
    private String sno;
    private String position;
}
