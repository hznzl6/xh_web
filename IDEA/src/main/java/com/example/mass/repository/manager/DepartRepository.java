package com.example.mass.repository.manager;

import com.example.mass.entity.manager.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartRepository extends JpaRepository<Department, Integer> {
    Department findByMass(String mass);

    Department findByMassAndDepartment(String mass, String depart);

    List<Department> findAllByMass(String mass);

    List<Department> findAll();

}
