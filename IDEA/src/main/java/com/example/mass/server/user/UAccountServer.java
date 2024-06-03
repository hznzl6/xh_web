package com.example.mass.server.user;

import com.example.mass.entity.user.Info;
import com.example.mass.repository.user.AccountRepository;

public interface UAccountServer {

//    注册
    boolean Register(String sno, String pwd);

//    登陆
    String Login(String sno, String pwd);

//    登出
    void Logout(String sno);

//    校验身份
    boolean VerifyLogin(String sno, String token);

//    判断是否完善
    boolean isPerfect(String sno);

//    完善信息
    void perfectInfo(String sno, String name, String sex, String belong, String mail);

//    获取信息
    Info getInfo(String sno);

//    更新信息
    void updateInfo(String sno, String name, String belong, String mail);

//    删除用户
    void Delete(String sno);

//    更新密码
    boolean UpdatePwd(String sno, String old, String nuw);

}
