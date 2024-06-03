package com.example.mass.server.manager;

public interface MAccountServer {
    String Login(String account, String pwd);

    void Logout(String account);

    boolean VerifyLogin(String account, String token);

    boolean UpdatePwd(String sno, String old, String nuw);


}
