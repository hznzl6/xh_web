package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.HoutaiAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoutaiAccountRepository extends JpaRepository<HoutaiAccount, Integer> {

    HoutaiAccount findByAccount(String account);

    HoutaiAccount findByAccountAndPwd(String account, String pwd);
}
