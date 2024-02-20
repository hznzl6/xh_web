package cn.ganxy03.xh_website.antity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "shetuan_credit")
public class ShetuanCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String semester;
    private String founder;
    private String declaration;
    private String deadline;
    private Integer status;

}
