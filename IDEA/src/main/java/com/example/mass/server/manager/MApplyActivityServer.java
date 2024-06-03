package com.example.mass.server.manager;

import com.example.mass.entity.manager.MApplyActivity;

import java.util.List;

public interface MApplyActivityServer {
    boolean applyNew(String mass, String title, String describe, String sno, String remark);

    void updateData(String mass, String title, String describe, String sno, String remark);

    void deleteData(String mass, String title);

    List<MApplyActivity> getActivities(String mass);

    List<MApplyActivity> getAll();
}
