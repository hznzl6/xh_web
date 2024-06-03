package com.example.mass.repository.manager;

import com.example.mass.entity.manager.MAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MAccountRepository extends JpaRepository<MAccount, Integer> {
    MAccount findByAccount(String account);

}
