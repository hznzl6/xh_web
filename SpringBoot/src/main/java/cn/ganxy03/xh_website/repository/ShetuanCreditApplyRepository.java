package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanCreditApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShetuanCreditApplyRepository extends JpaRepository<ShetuanCreditApply, Integer> {

    ShetuanCreditApply findBySemesterAndMass(String semester, String mass);

    List<ShetuanCreditApply> findAllByMass(String mass);

    List<ShetuanCreditApply> findAllBySemester(String semester);
}
