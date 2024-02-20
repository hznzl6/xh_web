package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.WenzhangArticle;

import java.util.List;

public interface WenzhangArticleService {

    boolean PostArticle(String mass, String title, String content, String author);

    boolean DeleteArticle(String mass, String title);

    void AgreeArticle(String mass, String title);

    void RejectArticle(String mass, String title);

    List<WenzhangArticle> GetAllArticle();

    List<WenzhangArticle> GetAllArticleByMass(String mass);


}
