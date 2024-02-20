package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.HoutaiSecure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoutaiSecureReposotory extends JpaRepository<HoutaiSecure, Integer> {

    List<HoutaiSecure> findAllByAccount(String account);

//    HoutaiSecure findByAccount(String account);
}
