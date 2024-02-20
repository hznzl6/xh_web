package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.UserSecure;

import java.util.List;

public interface UserSecureService {
    List<UserSecure> GetAllBySno(String sno);
}
