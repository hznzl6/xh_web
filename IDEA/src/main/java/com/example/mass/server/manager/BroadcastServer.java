package com.example.mass.server.manager;


import com.example.mass.entity.manager.Broadcast;

import java.util.List;

public interface BroadcastServer {
    boolean postBroadcast(String mass, String title, String content, String sno);

    void updateBroadcast(String mass, String title, String content);

    void deleteBroadcast(String mass, String title);

    List<Broadcast> getBroadcasts(String mass);

}
