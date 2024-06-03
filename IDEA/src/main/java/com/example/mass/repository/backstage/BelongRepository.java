package com.example.mass.repository.backstage;

import com.example.mass.entity.backstage.Belong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BelongRepository extends JpaRepository<Belong, Integer> {
    Belong findByBelong(String belong);

    List<Belong> findAllByPeriod(String period);

    List<Belong> findAllByStatus(Integer status);
}
