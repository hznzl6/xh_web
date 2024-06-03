package com.example.mass.server.backstage;

import com.example.mass.entity.backstage.Belong;

import java.util.List;

public interface BelongServer {
    boolean newOne(String period, String belong);

    void deleteOne(String belong);

    void disableOne(String belong);

    List<Belong> getBelongs();
}
