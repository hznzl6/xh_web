package com.example.mass.server.manager.impl;

import com.example.mass.entity.manager.ApplyArticle;
import com.example.mass.repository.manager.ApplyArticleRepository;
import com.example.mass.server.manager.ApplyArticleServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplyArticleImpl implements ApplyArticleServer {
    private final ApplyArticleRepository articleRepository;

    public ApplyArticleImpl(ApplyArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public boolean postArticle(String mass, String title, String content, String sno) {
        ApplyArticle article = articleRepository.findByMassAndTitle(mass, title);
        if(article == null) {
            ApplyArticle target = new ApplyArticle();
            target.setMass(mass);
            target.setTitle(title);
            target.setContent(content);
            target.setSno(sno);
            target.setStatus(0);
            target.setHits(0);
            target.setPost_time(LocalDateTime.now());
            articleRepository.save(target);
            return true;
        }
        return false;
    }

    public void updateArticle(String mass, String title, String content) {
        ApplyArticle applyArticle = articleRepository.findByMassAndTitle(mass, title);
        applyArticle.setContent(content);
        applyArticle.setUpdate_time(LocalDateTime.now());
        articleRepository.save(applyArticle);
    }

    public void deleteArticle(String mass, String title) {
        ApplyArticle article = articleRepository.findByMassAndTitle(mass, title);
        articleRepository.delete(article);
    }

    public List<ApplyArticle> getArticles(String mass) {
        return articleRepository.findAllByMass(mass);
    }

    public List<ApplyArticle> getAll() {
        return articleRepository.findAll();
    }
}
