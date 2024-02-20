package cn.ganxy03.xh_website.antity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shetuan_manager")
public class ShetuanManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mass;
    private String account;
    private String pwd;
    private String mail;
    private String qq;
    private String phone;
}
