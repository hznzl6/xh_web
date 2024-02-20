package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShetuanCreditRepository extends JpaRepository<ShetuanCredit, Integer> {
    ShetuanCredit findBySemester(String semester);

    List<ShetuanCredit> findAll();
}
