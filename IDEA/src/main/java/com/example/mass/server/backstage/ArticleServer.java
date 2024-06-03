package com.example.mass.server.backstage;


import com.example.mass.controller.manager.ApplyArticleController;
import com.example.mass.entity.backstage.Article;
import com.example.mass.entity.manager.ApplyArticle;

import java.util.List;

public interface ArticleServer {
    void PassArticle(String mass, String title);

    void RejectArticle(String mass, String title);

    List<ApplyArticle> getAll();

    ApplyArticle getOne(String mass, String title);
}
