package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanUser;

public interface ShetuanUserService {

    boolean Perfect(String sno,String nickname, String name, String belong, String qq, String mail, String phone);

    boolean UpdateInfo(String sno,String nickname, String belong, String qq, String phone);

    boolean DeleteUser(String sno);
}
