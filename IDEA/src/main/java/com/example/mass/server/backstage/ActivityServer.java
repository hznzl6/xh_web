package com.example.mass.server.backstage;

import com.example.mass.entity.backstage.Activity;
import com.example.mass.entity.manager.MApplyActivity;

import java.util.List;

public interface ActivityServer {

    void PassActivity(String mass, String title);

    void RejectActivity(String mass, String title);

    List<MApplyActivity> getAll(String mass);
}
