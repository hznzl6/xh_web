package com.example.mass.server.backstage.impl;

import com.example.mass.entity.backstage.Article;
import com.example.mass.entity.manager.ApplyArticle;
import com.example.mass.repository.manager.ApplyArticleRepository;
import com.example.mass.server.backstage.ArticleServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleImpl implements ArticleServer {
    private final ApplyArticleRepository applyArticleRepository;

    public ArticleImpl(ApplyArticleRepository applyArticleRepository) {
        this.applyArticleRepository = applyArticleRepository;
    }

    public void PassArticle(String mass, String title) {
        ApplyArticle article = applyArticleRepository.findByMassAndTitle(mass, title);

        article.setStatus(1);
        article.setOperate_time(LocalDateTime.now());
        applyArticleRepository.save(article);
    }

    public void RejectArticle(String mass, String title) {
        ApplyArticle article = applyArticleRepository.findByMassAndTitle(mass, title);

        article.setStatus(2);
        article.setOperate_time(LocalDateTime.now());
        applyArticleRepository.save(article);
    }

    public List<ApplyArticle> getAll() {
        return applyArticleRepository.findAll();
    }

    public ApplyArticle getOne(String mass, String title) {
        return applyArticleRepository.findByMassAndTitle(mass, title);
    }
}
