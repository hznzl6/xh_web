package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanActivity;

import java.util.List;

public interface ShetuanActivityService {

    boolean ApplyEvent(String mass, String event, String describes, String person, String phone, String expenditure, String start_time, String end_time, String mark);

    boolean AgreeEvent(String mass, String event);

    boolean RejectEvent(String mass, String event);

    boolean OverEvent(String mass, String event);

    List<ShetuanActivity> GetActiveEvent(String mass);

    List<ShetuanActivity> findAllByMass(String mass);

    List<ShetuanActivity> getAllByMassAndStatus(String mass);

    List<ShetuanActivity> getAllActivity();

    boolean SignEvent(String sno, String mass, String event);

    boolean ActualEvent(String mass, String event, String sno);

    Integer YiActualEvent(String mass, String event, String snos);
}
