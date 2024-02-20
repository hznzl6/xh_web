package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.HoutaiSite;

import java.util.List;

public interface HoutaiSiteArticleService {
    boolean SaveArticle(String belong, String title, String content);

    HoutaiSite GetArticle(String belong, String title);

    List<HoutaiSite> GetAllSites();
}
