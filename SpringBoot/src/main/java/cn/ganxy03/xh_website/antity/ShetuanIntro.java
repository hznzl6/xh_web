package cn.ganxy03.xh_website.antity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shetuan_intro")
public class ShetuanIntro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mass;
    private String title;
    private String html;
    private String charge;
    private String sno;
    private String phone;
    private String teacher;
    private String gonghao;
    private String setup;
    private int memnums;
    private int acnums;
    private int artnums;
    private int hitnums;
    private int comnums;
    private int bronums;
    private int nums0;
    private int crenums;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime update_time;


}
