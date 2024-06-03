package com.example.mass.server.manager;

import com.example.mass.entity.backstage.format.FMember;
import com.example.mass.entity.manager.Member;
import com.example.mass.entity.user.Info;

import java.util.List;

public interface MemberServer {
    void DeleteOne(String mass, String depart, String sno);

    void UpdateOne(String sno, String name, String sex, String belong, String mail);

    Info GetOne(String sno);

    List<Member> getMembers(String mass);

    List<FMember> getAll();
}
