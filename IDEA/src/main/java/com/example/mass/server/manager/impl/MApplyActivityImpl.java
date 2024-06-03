package com.example.mass.server.manager.impl;

import com.example.mass.entity.manager.MApplyActivity;
import com.example.mass.repository.manager.MApplyActivityRepository;
import com.example.mass.server.manager.MApplyActivityServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MApplyActivityImpl implements MApplyActivityServer {
    private final MApplyActivityRepository mApplyActivityRepository;

    public MApplyActivityImpl(MApplyActivityRepository mApplyActivityRepository) {
        this.mApplyActivityRepository = mApplyActivityRepository;
    }

    public boolean applyNew(String mass, String title, String describe, String sno, String remark) {
        MApplyActivity target = mApplyActivityRepository.findByMassAndTitle(mass, title);
        if(target == null) {
            MApplyActivity mApplyActivity = new MApplyActivity();
            mApplyActivity.setMass(mass);
            mApplyActivity.setTitle(title);
            mApplyActivity.setDescription(describe);
            mApplyActivity.setSno(sno);
            mApplyActivity.setRemark(remark);
            mApplyActivity.setStatus(0);
            mApplyActivity.setApply_time(LocalDateTime.now());
            mApplyActivityRepository.save(mApplyActivity);
            return true;
        }
        return false;
    }

    public void updateData(String mass, String title, String describe, String sno, String remark) {
        MApplyActivity mApplyActivity = mApplyActivityRepository.findByMassAndTitle(mass, title);
        mApplyActivity.setTitle(title);
        mApplyActivity.setDescription(describe);
        mApplyActivity.setSno(sno);
        mApplyActivity.setRemark(remark);
        mApplyActivity.setUpdate_time(LocalDateTime.now());
        mApplyActivityRepository.save(mApplyActivity);
    }

    public void deleteData(String mass, String title){
        MApplyActivity mApplyActivity = mApplyActivityRepository.findByMassAndTitle(mass, title);
        mApplyActivityRepository.delete(mApplyActivity);
    }

    public List<MApplyActivity> getActivities(String mass){
        return mApplyActivityRepository.findAllByMass(mass);
    }

    public List<MApplyActivity> getAll(){
        return mApplyActivityRepository.findAll();
    }
}
