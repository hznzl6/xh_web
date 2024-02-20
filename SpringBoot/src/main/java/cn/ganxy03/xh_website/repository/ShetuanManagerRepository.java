package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShetuanManagerRepository extends JpaRepository<ShetuanManager, Integer> {

    ShetuanManager getByMass(String mass);

    ShetuanManager getByAccount(String account);

}
