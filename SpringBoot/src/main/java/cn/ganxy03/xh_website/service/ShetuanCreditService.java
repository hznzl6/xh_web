package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanCredit;

import java.util.List;

public interface ShetuanCreditService {
    boolean Create(String semester, String founder, String declaration, String deadline);

    List<ShetuanCredit> GetAll();

    void JudgeStatus();
}
