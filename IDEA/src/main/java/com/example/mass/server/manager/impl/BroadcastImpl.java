package com.example.mass.server.manager.impl;

import com.example.mass.entity.manager.Broadcast;
import com.example.mass.repository.manager.BroadcastRepository;
import com.example.mass.server.manager.BroadcastServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BroadcastImpl implements BroadcastServer {
    private final BroadcastRepository broadcastRepository;

    public BroadcastImpl(BroadcastRepository broadcastRepository) {
        this.broadcastRepository = broadcastRepository;
    }

    public boolean postBroadcast(String mass, String title, String content, String sno) {
        Broadcast target = broadcastRepository.findByMassAndTitle(mass, title);
        if(target == null) {
            Broadcast broadcast = new Broadcast();
            broadcast.setMass(mass);
            broadcast.setTitle(title);
            broadcast.setContent(content);
            broadcast.setSno(sno);
            broadcast.setHits(0);
            broadcast.setCreate_time(LocalDateTime.now());
            broadcastRepository.save(broadcast);
            return true;
        }
        return false;
    }

    public void updateBroadcast(String mass, String title, String content) {
        Broadcast broadcast = broadcastRepository.findByMassAndTitle(mass, title);
        broadcast.setContent(content);
        broadcast.setUpdate_time(LocalDateTime.now());
        broadcastRepository.save(broadcast);
    }

    public void deleteBroadcast(String mass, String title) {
        Broadcast target = broadcastRepository.findByMassAndTitle(mass, title);
        broadcastRepository.delete(target);
    }

    public List<Broadcast> getBroadcasts(String mass) {
        return broadcastRepository.findAllByMass(mass);
    }
}
