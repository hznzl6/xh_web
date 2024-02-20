package cn.ganxy03.xh_website.antity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shetuan_list")
public class ShetuanMass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String info;
    private int num;
    private String qq;
    private String president;
    private String member;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String create_time;
}
