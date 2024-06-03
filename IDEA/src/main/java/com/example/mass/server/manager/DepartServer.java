package com.example.mass.server.manager;


import com.example.mass.entity.manager.Department;

import java.util.List;

public interface DepartServer {
    boolean newDepart(String mass, String depart);

    void delDepart(String mass, String depart);

    List<Department> getDepart(String mass);

    List<Department> getAll();

}
