package cn.ganxy03.xh_website.antity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shetuan_login")
public class ShetuanLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sno;
    private String pwd;
    private String mail;
    private String phone;
    private Integer perfect;
}
