package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShetuanUserRepository extends JpaRepository<ShetuanUser, Integer> {

    boolean existsBySno(String sno);

    ShetuanUser getBySno(String sno);

    ShetuanUser getAllById(Integer id);
}
