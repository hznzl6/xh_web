package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.HoutaiSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoutaiSiteRepository extends JpaRepository<HoutaiSite, Integer> {

    HoutaiSite findByBelongAndTitle(String belong, String title);

    List<HoutaiSite> findAll();
}
