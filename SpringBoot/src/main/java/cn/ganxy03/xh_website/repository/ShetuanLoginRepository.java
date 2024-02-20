package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShetuanLoginRepository extends JpaRepository<ShetuanLogin, Integer> {

    ShetuanLogin findBySno(String sno);

    boolean existsBySno(String sno);
}
