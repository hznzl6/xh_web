package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.UserSecure;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSecureRepository extends JpaRepository<UserSecure, Integer> {
    List<UserSecure> findAllBySno(String sno, Sort sort);

    UserSecure findBySno(String sno);
}
