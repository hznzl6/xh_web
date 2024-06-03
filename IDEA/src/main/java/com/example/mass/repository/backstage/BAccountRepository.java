package com.example.mass.repository.backstage;

import com.example.mass.entity.backstage.BAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BAccountRepository extends JpaRepository<BAccount, Integer> {

    BAccount findByAccount(String account);


}
