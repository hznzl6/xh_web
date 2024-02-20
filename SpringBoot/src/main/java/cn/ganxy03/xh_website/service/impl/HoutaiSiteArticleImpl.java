package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.HoutaiSite;
import cn.ganxy03.xh_website.repository.HoutaiSiteRepository;
import cn.ganxy03.xh_website.service.HoutaiSiteArticleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HoutaiSiteArticleImpl implements HoutaiSiteArticleService {
    private final HoutaiSiteRepository houtaiSiteRepository;
    public HoutaiSiteArticleImpl(HoutaiSiteRepository houtaiSiteRepository) {
        this.houtaiSiteRepository = houtaiSiteRepository;
    }
    @Override
    public boolean SaveArticle(String belong, String title, String content) {
        HoutaiSite houtaiSiteArticle = houtaiSiteRepository.findByBelongAndTitle(belong, title);
        if(houtaiSiteArticle == null) {
            HoutaiSite houtaiSiteArticle1 = new HoutaiSite();
            houtaiSiteArticle1.setBelong(belong);
            houtaiSiteArticle1.setTitle(title);
            houtaiSiteArticle1.setContent(content);
            houtaiSiteArticle1.setCreate_time(LocalDateTime.now());
            houtaiSiteRepository.save(houtaiSiteArticle1);
            return true;
        } else {
            houtaiSiteArticle.setContent(content);
            houtaiSiteArticle.setUpdate_time(LocalDateTime.now());
            houtaiSiteRepository.save(houtaiSiteArticle);
            return true;
        }
    }

    @Override
    public HoutaiSite GetArticle(String belong, String title) {
        HoutaiSite houtaiSite = houtaiSiteRepository.findByBelongAndTitle(belong, title);
        if(houtaiSite != null) {
            return houtaiSite;
        } else {
            return null;
        }
    }

    @Override
    public List<HoutaiSite> GetAllSites() {
        List<HoutaiSite> houtaiSites = houtaiSiteRepository.findAll();
        return houtaiSites;
    }
}
