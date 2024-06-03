package com.example.mass.repository.backstage;

import com.example.mass.entity.backstage.MassList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MassListRepository extends JpaRepository<MassList, Integer> {
    MassList findByMass(String mass);

    List<MassList> findAll();
}
