package com.example.mass.repository.manager;

import com.example.mass.entity.manager.Broadcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BroadcastRepository extends JpaRepository<Broadcast, Integer> {
    Broadcast findByMassAndTitle(String mass, String title);

    List<Broadcast> findAllByMass(String mass);
}
