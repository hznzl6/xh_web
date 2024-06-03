package com.example.mass.server.manager;

import com.example.mass.entity.manager.ApplyArticle;

import java.util.List;

public interface ApplyArticleServer {
    boolean postArticle(String mass, String title, String content, String sno);

    void updateArticle(String mass, String title, String content);

    void deleteArticle(String mass, String title);

    List<ApplyArticle> getArticles(String Mass);

    List<ApplyArticle> getAll();
}
