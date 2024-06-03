package com.example.mass.repository.backstage;

import com.example.mass.entity.backstage.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Integer> {
    QuestionBank findByStem(String stem);

    List<QuestionBank> findAll();
}
