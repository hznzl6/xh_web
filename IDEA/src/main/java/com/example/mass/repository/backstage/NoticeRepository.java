package com.example.mass.repository.backstage;

import com.example.mass.entity.backstage.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Notice findByTitle(String title);

    List<Notice> findAll();


}
