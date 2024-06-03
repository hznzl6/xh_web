package com.example.mass.server.user.impl;

import com.example.mass.entity.user.UApplyActivity;
import com.example.mass.repository.user.UApplyActivityRepository;
import com.example.mass.server.user.UApplyActivityServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UApplyActivityImpl implements UApplyActivityServer {
    private final UApplyActivityRepository uApplyActivityRepository;

    public UApplyActivityImpl(UApplyActivityRepository uApplyActivityRepository) {
        this.uApplyActivityRepository = uApplyActivityRepository;
    }

    public boolean applyJoin(Integer target, String mass, String sno) {
        UApplyActivity uApplyActivity = uApplyActivityRepository.findByTargetAndSno(target, sno);
        if(uApplyActivity == null) {
            UApplyActivity uApplyActivity1 = new UApplyActivity();
            uApplyActivity1.setTarget(target);
            uApplyActivity1.setMass(mass);
            uApplyActivity1.setSno(sno);
            uApplyActivity1.setStatus(0);
            uApplyActivity1.setApply_time(LocalDateTime.now());
            uApplyActivityRepository.save(uApplyActivity1);
            return true;
        }
        return false;
    }

    public void deleteApply(Integer target, String sno) {
        UApplyActivity uApplyActivity = uApplyActivityRepository.findByTargetAndSno(target, sno);
        uApplyActivityRepository.delete(uApplyActivity);
    }

    public List<UApplyActivity> getActivities(String mass) {
        return uApplyActivityRepository.findAllByMass(mass);
    }

    public List<UApplyActivity> getJoinActivities(String mass, String sno) {
        return uApplyActivityRepository.findByMassAndSno(mass, sno);
    }
}
