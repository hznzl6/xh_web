package com.example.mass.entity.manager;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "manager_account")
public class MAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String account;
    private String pwd;
    private Integer status;
}
