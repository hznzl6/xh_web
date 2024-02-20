package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanIntro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShetuanIntroRepository extends JpaRepository<ShetuanIntro, Integer> {
    ShetuanIntro getByMass(String mass);

    List<ShetuanIntro> getListByMass(String mass);
}
