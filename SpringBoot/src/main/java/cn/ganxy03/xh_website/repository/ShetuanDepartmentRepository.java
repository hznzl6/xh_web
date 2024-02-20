package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShetuanDepartmentRepository extends JpaRepository<ShetuanDepartment, Integer> {

    List<ShetuanDepartment> findAllByMass(String mass);

    ShetuanDepartment findAllByMassAndDepartment(String mass, String department);
}
