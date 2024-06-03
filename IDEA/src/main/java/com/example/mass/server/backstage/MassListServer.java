package com.example.mass.server.backstage;

import com.example.mass.entity.backstage.MassList;

import java.util.List;

public interface MassListServer {
    boolean NewOne(String mass);

    MassList GetOne(String mass);

    void UpdateOne(String mass, String manager, String slogan, String teacher);

    void UpdateInfo(String mass, String info);

    void DeleteOne(String mass);

    List<MassList> getAll();
}
