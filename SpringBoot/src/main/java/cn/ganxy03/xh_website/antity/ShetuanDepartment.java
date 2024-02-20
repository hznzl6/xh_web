package cn.ganxy03.xh_website.antity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shetuan_department")
public class ShetuanDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mass;
    private String department;
    private int priority;
    private int nums;

}
