package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShetuanActivityRepository extends JpaRepository<ShetuanActivity, Integer> {

    ShetuanActivity getByMassAndEvent(String mass, String event);


    List<ShetuanActivity> findAllByMassAndStatus(String mass, Integer status);

    List<ShetuanActivity> findAllByMass(String mass);

}
