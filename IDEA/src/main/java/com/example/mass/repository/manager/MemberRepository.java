package com.example.mass.repository.manager;

import com.example.mass.entity.manager.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByMassAndSno(String mass, String sno);

    List<Member> findAllByMass(String mass);

}
