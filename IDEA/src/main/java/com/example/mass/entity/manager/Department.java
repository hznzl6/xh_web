package com.example.mass.entity.manager;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manager_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mass;
    private String department;
    private Integer priority;
    private Integer nums;
}
