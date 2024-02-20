package cn.ganxy03.xh_website.antity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shetuan_apply")
public class ShetuanApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sno;
    private String mass;
    private String department;
    private String info;
    private String position;
    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime apply_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime update_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operate_time;

    @OneToOne
    @JoinColumn(name = "id")
    private ShetuanUser shetuanUser;
    public void setShetuanUser(ShetuanUser shetuanUser) {
        this.shetuanUser = shetuanUser;
    }
}
