package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanApply;

import java.util.List;

public interface ShetuanApplyService {
    Integer PostApplication(String sno, String mass, String department, String info);

    Integer UpdateApplication(String sno, String mass, String department, String info);

    Integer AgreeApplication(String sno, String mass, String department);

    Integer RejectApplication(String mass, String sno);

    List<ShetuanApply> GetWaitAgree(String mass);

    Integer GetStatus(String sno);

    List<ShetuanApply> GetAllPassByMass(String mass);

    boolean UpdateInfo(String mass, String sno, String beforeDepartment, String department, String position);

    boolean DeleteMember(String mass, String sno, String department);

    List<ShetuanApply> GetAllJoinMass(String sno);

    List<ShetuanApply> GetAllCheckBySno(String sno);
}
