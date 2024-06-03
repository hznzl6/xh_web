package com.example.mass.repository.user;

import com.example.mass.entity.user.UApplyActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UApplyActivityRepository extends JpaRepository<UApplyActivity, Integer> {

    List<UApplyActivity> findAllByMass(String mass);

    UApplyActivity findByTargetAndSno(Integer target, String sno);

    List<UApplyActivity> findByMassAndSno(String mass, String sno);
}
