package com.example.mass.server.manager.impl;

import com.example.mass.entity.manager.Department;
import com.example.mass.repository.manager.DepartRepository;
import com.example.mass.server.manager.DepartServer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartImpl implements DepartServer {
    private final DepartRepository departRepository;

    public DepartImpl(DepartRepository departRepository) {
        this.departRepository = departRepository;
    }

    public boolean newDepart(String mass, String depart) {
        Department target = departRepository.findByMassAndDepartment(mass, depart);
        if(target != null) {
            return false;
        }
        Department department = new Department();
        department.setMass(mass);
        department.setDepartment(depart);
        department.setPriority(2);
        department.setNums(0);
        departRepository.save(department);
        return true;
    }

    public void delDepart(String mass, String depart) {
        Department target = departRepository.findByMassAndDepartment(mass, depart);
        departRepository.delete(target);
    }

    public List<Department> getDepart(String mass) {
        List<Department> departments = departRepository.findAllByMass(mass);
        return departments;
    }

    public List<Department> getAll() {
        return departRepository.findAll();
    }
}
