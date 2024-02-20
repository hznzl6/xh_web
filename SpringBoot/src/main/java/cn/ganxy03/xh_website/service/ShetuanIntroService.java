package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanIntro;

import java.util.List;

public interface ShetuanIntroService {
    boolean UpdateIntro(String mass, String title, String html);

    List<ShetuanIntro> getIntro(String mass);

//    void SetZero();
}
