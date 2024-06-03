package com.example.mass.repository.user;

import com.example.mass.entity.user.ApplyMass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyMassRepository extends JpaRepository<ApplyMass, Integer> {
    List<ApplyMass> findAllByMass(String mass);

    ApplyMass findByMassAndSno(String mass, String sno);
}
