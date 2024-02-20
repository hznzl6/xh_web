package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanLogin;

import javax.servlet.http.HttpServletRequest;

public interface ShetuanLoginService {

    ShetuanLogin RegisterByMail(String sno, String pwd, String mail);

    ShetuanLogin RegisterByPhone(String sno, String pwd, String phone);

    boolean login(String sno, String password, HttpServletRequest request);

    ShetuanLogin getInfoBySno(String sno);

    boolean updatePassword(String sno, String password);

}
