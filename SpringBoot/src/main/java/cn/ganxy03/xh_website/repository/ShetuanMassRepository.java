package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanMass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShetuanMassRepository extends JpaRepository<ShetuanMass, Integer> {
    ShetuanMass findByName(String name);
}
