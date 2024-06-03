package com.example.mass.server.user;

import com.example.mass.entity.user.UApplyActivity;

import java.util.List;

public interface UApplyActivityServer {
    boolean applyJoin(Integer target, String mass, String sno);

    void deleteApply(Integer target, String sno);

    List<UApplyActivity> getActivities(String mass);

    List<UApplyActivity> getJoinActivities(String mass, String sno);
}
