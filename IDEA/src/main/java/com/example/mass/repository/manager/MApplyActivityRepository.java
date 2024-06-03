package com.example.mass.repository.manager;

import com.example.mass.entity.manager.MApplyActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MApplyActivityRepository extends JpaRepository<MApplyActivity, Integer> {

    List<MApplyActivity> findAllByMass(String mass);

    MApplyActivity findByMassAndTitle(String mass, String title);

    List<MApplyActivity> findAll();
}

