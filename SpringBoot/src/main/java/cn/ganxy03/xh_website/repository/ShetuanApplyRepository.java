package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShetuanApplyRepository extends JpaRepository<ShetuanApply, Integer> {

    ShetuanApply findBySno(String sno);

    List<ShetuanApply> findAllByMassAndStatus(String mass, Integer status);

    ShetuanApply findByMassAndSno(String mass, String sno);

    List<ShetuanApply> findAllBySnoAndStatus(String sno, Integer status);

    List<ShetuanApply> findAllBySno(String sno);
}
