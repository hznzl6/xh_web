package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.DepartmentNode;
import cn.ganxy03.xh_website.antity.ShetuanDepartment;

import java.util.List;

public interface ShetuanDepartmentService {

//    List<ShetuanDepartment> GetAllDepartmentByMass(String mass);
    List<DepartmentNode> GetAllDepartmentByMass(String mass);

    boolean CreateDepartment(String mass, String department);

    boolean DeleteDepartment(String mass, String department);

    List<ShetuanDepartment> GetJustDepartmentByMass(String mass);
}

