package com.example.mass.server.backstage;

public interface BAccountServer {
    String Login(String account, String pwd);

    void Logout(String account);

    boolean Verify(String account, String token);

    boolean UpdatePwd(String sno, String old, String nuw);
}
