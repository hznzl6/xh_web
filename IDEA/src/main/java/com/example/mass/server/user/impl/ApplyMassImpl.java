package com.example.mass.server.user.impl;

import com.example.mass.entity.manager.Department;
import com.example.mass.entity.manager.Member;
import com.example.mass.entity.user.ApplyMass;
import com.example.mass.repository.manager.DepartRepository;
import com.example.mass.repository.manager.MemberRepository;
import com.example.mass.repository.user.ApplyMassRepository;
import com.example.mass.server.user.ApplyMassServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplyMassImpl implements ApplyMassServer {
    private final ApplyMassRepository applyMassRepository;
    private final MemberRepository memberRepository;
    private final DepartRepository departRepository;

    public ApplyMassImpl(ApplyMassRepository applyMassRepository, MemberRepository memberRepository, DepartRepository departRepository) {
        this.applyMassRepository = applyMassRepository;
        this.memberRepository = memberRepository;
        this.departRepository = departRepository;
    }

    public List<ApplyMass> getApplies(String mass) {
        return applyMassRepository.findAllByMass(mass);
    }

    public void AgreeJoin(String mass, String depart, String sno) {
        Member member = new Member();
        member.setMass(mass);
        member.setDepart(depart);
        member.setSno(sno);
        member.setPosition("成员");
        memberRepository.save(member);

        ApplyMass target = applyMassRepository.findByMassAndSno(mass, sno);
        target.setApply_time(LocalDateTime.now());
        target.setStatus(1);
        applyMassRepository.save(target);

        Department department = departRepository.findByMassAndDepartment(mass, depart);
        department.setNums(department.getNums() + 1);
        departRepository.save(department);
    }

    public void RejectJoin(String mass, String sno) {
        ApplyMass target = applyMassRepository.findByMassAndSno(mass, sno);
        target.setApply_time(LocalDateTime.now());
        target.setStatus(2);
        applyMassRepository.save(target);
    }
}
