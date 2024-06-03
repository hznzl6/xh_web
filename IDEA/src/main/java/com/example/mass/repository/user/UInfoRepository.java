package com.example.mass.repository.user;

import com.example.mass.entity.user.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UInfoRepository extends JpaRepository<Info, Integer> {
    Info findBySno(String sno);
}
