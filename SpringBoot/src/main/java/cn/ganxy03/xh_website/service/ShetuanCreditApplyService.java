package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanCreditApply;
import cn.ganxy03.xh_website.antity.ShetuanUser;

import java.util.List;

public interface ShetuanCreditApplyService {

    boolean ApplyCredit(String[] snos, String semester, String mass);

    void AddCredit(String[] snos, String semester, String mass);

    void RemoveCredit(String sno, String semester, String mass);

    void PassCredit(String semester, String mass);

    void ReBackCredit(String semester, String mass);

    List<ShetuanCreditApply> GetItemByMass(String mass);

    List<ShetuanCreditApply> GetAllCredit();

    Integer SearchId(String sno);

    String SearchBelong(String sno);

    List<ShetuanUser> GetInfoById(String[] ids);
}
