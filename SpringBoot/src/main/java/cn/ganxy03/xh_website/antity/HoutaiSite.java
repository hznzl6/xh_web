package cn.ganxy03.xh_website.antity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "houtai_site")
public class HoutaiSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String belong;
    private String title;
    private String content;
    private String author;
    private Integer hits;

    @JsonFormat (pattern = "yyyy-MM:dd HH:mm:ss")
    private LocalDateTime create_time;
    @JsonFormat (pattern = "yyyy-MM:dd HH:mm:ss")
    private LocalDateTime update_time;
}
