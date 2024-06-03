package com.example.mass.server.backstage.impl;

import com.example.mass.entity.backstage.Activity;
import com.example.mass.entity.manager.MApplyActivity;
import com.example.mass.repository.manager.MApplyActivityRepository;
import com.example.mass.server.backstage.ActivityServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityImpl implements ActivityServer {
    private final MApplyActivityRepository mApplyActivityRepository;

    public ActivityImpl(MApplyActivityRepository mApplyActivityRepository) {
        this.mApplyActivityRepository = mApplyActivityRepository;
    }

    public void PassActivity(String mass, String title) {
        MApplyActivity mApplyActivity = mApplyActivityRepository.findByMassAndTitle(mass, title);
        mApplyActivity.setStatus(1);
        mApplyActivity.setOperate_time(LocalDateTime.now());
        mApplyActivityRepository.save(mApplyActivity);
    }

    public void RejectActivity(String mass, String title) {
        MApplyActivity mApplyActivity = mApplyActivityRepository.findByMassAndTitle(mass, title);
        mApplyActivity.setStatus(2);
        mApplyActivity.setOperate_time(LocalDateTime.now());
        mApplyActivityRepository.save(mApplyActivity);
    }

    public List<MApplyActivity> getAll(String mass) {
        return mApplyActivityRepository.findAllByMass(mass);
    }
}
