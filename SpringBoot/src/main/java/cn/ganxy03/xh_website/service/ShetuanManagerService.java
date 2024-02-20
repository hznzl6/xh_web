package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanManager;

public interface ShetuanManagerService {

    boolean GenerateAccount(String mass);

    boolean Updatepwd(String account, String pwd);

    boolean UpdateInfo(String account, String mail, String qq, String phone);

    boolean Login(String account, String pwd);

    ShetuanManager GetInfo(String account);

    String ReturnMass(String account);
}
