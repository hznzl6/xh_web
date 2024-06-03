package com.example.mass.server.backstage.impl;

import com.example.mass.entity.backstage.Belong;
import com.example.mass.repository.backstage.BelongRepository;
import com.example.mass.server.backstage.BelongServer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BelongImpl implements BelongServer {
    private final BelongRepository belongRepository;

    public BelongImpl(BelongRepository belongRepository) {
        this.belongRepository = belongRepository;
    }

    public boolean newOne(String period, String belong) {
        Belong target = belongRepository.findByBelong(belong);
        if(target == null) {
            Belong belong1 = new Belong();
            belong1.setPeriod(period);
            belong1.setBelong(belong);
            belong1.setStatus(0);
            belongRepository.save(belong1);
            return true;
        }
        return false;
    }

    public void deleteOne(String belong) {
        Belong belong1 = belongRepository.findByBelong(belong);
        belongRepository.delete(belong1);
    }

    public void disableOne(String belong) {
        Belong belong1 = belongRepository.findByBelong(belong);
        belong1.setStatus(1);
        belongRepository.save(belong1);
    }

    public List<Belong> getBelongs() {
        return belongRepository.findAll();
    }
}
