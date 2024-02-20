package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.HoutaiAccount;

import javax.servlet.http.HttpServletRequest;

public interface HoutaiAccountService {

    void CreateAccount(String role);

    void ChangeRole(String account, String role);

    void DeleteAccount(String account);

    void InitPwd(String account);

    void ChangePwd(String account, String pwd);

    Integer Login(String account, String pwd, HttpServletRequest request);

    HoutaiAccount GetInfo(String account);

}
