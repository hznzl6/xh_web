package com.example.mass.server.backstage.impl;

import com.example.mass.entity.backstage.MassList;
import com.example.mass.repository.backstage.MassListRepository;
import com.example.mass.server.backstage.MassListServer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MassListImpl implements MassListServer {

    private final MassListRepository massListRepository;

    public MassListImpl(MassListRepository massListRepository) {
        this.massListRepository = massListRepository;
    }

    public boolean NewOne(String mass) {
        MassList target = massListRepository.findByMass(mass);

        if(target == null) {
            MassList massList = new MassList();
            massList.setMass(mass);
            massListRepository.save(massList);
            return true;
        }
        return false;
    }

    public MassList GetOne(String mass) {
        return massListRepository.findByMass(mass);
    }

    public void UpdateOne(String mass, String manager, String slogan, String teacher) {
        MassList massList = massListRepository.findByMass(mass);
        massList.setManager(manager);
        massList.setSlogan(slogan);
        massList.setTeacher(teacher);
        massListRepository.save(massList);
    }

    public void UpdateInfo(String mass, String info) {
        MassList massList = massListRepository.findByMass(mass);
        massList.setInfo(info);
        massListRepository.save(massList);
    }

    public void DeleteOne(String mass) {
        MassList massList = massListRepository.findByMass(mass);
        massListRepository.delete(massList);
    }
    public List<MassList> getAll() {
        return massListRepository.findAll();
    }

}
