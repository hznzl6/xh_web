package com.example.mass.repository.user;

import com.example.mass.entity.user.UAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<UAccount, Integer> {
    UAccount findBySno(String sno);

}
