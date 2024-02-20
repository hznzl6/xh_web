package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanMass;
import cn.ganxy03.xh_website.antity.ShetuanUser;

import java.util.List;

public interface ShetuanMassService {
    boolean Update(String name, String info, String qq, String president, String create_time);

    List<ShetuanUser> GetMember(String mass);
}
