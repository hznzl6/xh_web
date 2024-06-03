package com.example.mass.repository.manager;

import com.example.mass.entity.manager.ApplyArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyArticleRepository extends JpaRepository<ApplyArticle, Integer> {
    ApplyArticle findByMassAndTitle(String mass, String title);

    List<ApplyArticle> findAllByMass(String mass);

    List<ApplyArticle> findAll();
}
