package com.example.mass.server.manager.impl;

import com.example.mass.entity.backstage.format.FMember;
import com.example.mass.entity.manager.Department;
import com.example.mass.entity.manager.Member;
import com.example.mass.entity.user.Info;
import com.example.mass.repository.manager.DepartRepository;
import com.example.mass.repository.manager.MemberRepository;
import com.example.mass.repository.user.UInfoRepository;
import com.example.mass.server.manager.MemberServer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberImpl implements MemberServer {
    private final MemberRepository memberRepository;
    private final DepartRepository departRepository;
    private final UInfoRepository uInfoRepository;

    public MemberImpl(MemberRepository memberRepository, DepartRepository departRepository, UInfoRepository uInfoRepository) {
        this.memberRepository = memberRepository;
        this.departRepository = departRepository;
        this.uInfoRepository = uInfoRepository;
    }

    public List<Member> getMembers(String mass) {
        return memberRepository.findAllByMass(mass);
    }

    public void UpdateOne(String sno, String name, String sex, String belong, String mail) {
        Info info = uInfoRepository.findBySno(sno);
        info.setName(name);
        info.setSex(sex);
        info.setBelong(belong);
        info.setMail(mail);
        uInfoRepository.save(info);
    }

    public Info GetOne(String sno) {
        return uInfoRepository.findBySno(sno);
    }

    public List<FMember> getAll() {
        List<Member> members = memberRepository.findAll();
        List<FMember> fMembers = new ArrayList<>();
        for (Member member : members) {
            String sno = member.getSno();
            Info uInfo = uInfoRepository.findBySno(sno);
            FMember fMember = new FMember();
            fMember.setMass(member.getMass());
            fMember.setSno(member.getSno());
            fMember.setName(uInfo.getName());
            fMember.setSex(uInfo.getSex());
            fMember.setBelong(uInfo.getBelong());
            fMember.setMail(uInfo.getMail());
            fMembers.add(fMember);
        }
        return fMembers;
    }

    public void DeleteOne(String mass, String depart, String sno) {
        Member target = memberRepository.findByMassAndSno(mass, sno);
        memberRepository.delete(target);

        Department department = departRepository.findByMassAndDepartment(mass, depart);
        department.setNums(department.getNums() - 1);
    }
}
