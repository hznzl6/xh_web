package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.HoutaiSecure;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface HoutaiSecureService {

    List<HoutaiSecure> GetAllByAccount(String account);
}
