package com.example.mass.server.user;

import com.example.mass.entity.user.ApplyMass;

import java.util.List;

public interface ApplyMassServer {
    List<ApplyMass> getApplies(String mass);

    void AgreeJoin(String mass, String depart, String sno);

    void RejectJoin(String mass, String sno);
}
