package com.example.mass.entity.backstage;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "houtai_account")
public class BAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String account;
    private String pwd;
    private String role;
    private Integer status;
}
