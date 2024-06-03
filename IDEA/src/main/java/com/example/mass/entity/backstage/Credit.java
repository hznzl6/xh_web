package com.example.mass.entity.backstage;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "manager_applyCredit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String semester;
}
