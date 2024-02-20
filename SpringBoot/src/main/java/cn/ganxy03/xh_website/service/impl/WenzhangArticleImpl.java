package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.WenzhangArticle;
import cn.ganxy03.xh_website.repository.WenzhangArticleRepository;
import cn.ganxy03.xh_website.service.WenzhangArticleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class WenzhangArticleImpl implements WenzhangArticleService {
    private final WenzhangArticleRepository wenzhangArticleRepository;

    public WenzhangArticleImpl(WenzhangArticleRepository wenzhangArticleRepository) {
        this.wenzhangArticleRepository = wenzhangArticleRepository;
    }

    @Override
    public boolean PostArticle(String mass, String title, String content, String author) {
        WenzhangArticle wenzhangArticle = wenzhangArticleRepository.findByMassAndTitleAndStatus(mass, title, 0);
        WenzhangArticle targetArticle;
        boolean isPost = false;
        if (wenzhangArticle != null) {
            targetArticle = wenzhangArticle;
            targetArticle.setUpdate_time(LocalDateTime.now());
        } else {
            targetArticle = new WenzhangArticle();
            targetArticle.setCreate_time(LocalDateTime.now());
            isPost = true;
        }
        targetArticle.setMass(mass);
        targetArticle.setTitle(title);
        targetArticle.setContent(content);
        targetArticle.setAuthor(author);
        targetArticle.setStatus(0);
        wenzhangArticleRepository.save(targetArticle);
        return isPost;
    }

    @Override
    public boolean DeleteArticle(String mass, String title) {
        WenzhangArticle wenzhangArticle = wenzhangArticleRepository.findByMassAndTitleAndStatus(mass, title, 0);
        if (wenzhangArticle != null) {
            wenzhangArticle.setStatus(1);
            wenzhangArticleRepository.save(wenzhangArticle);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<WenzhangArticle> GetAllArticleByMass(String mass) {
        List<WenzhangArticle> wenzhangArticles = wenzhangArticleRepository.findAllByMass(mass);
        return wenzhangArticles;
    }

    @Override
    public void AgreeArticle(String mass, String title) {
        WenzhangArticle wenzhangArticle = wenzhangArticleRepository.findByMassAndTitle(mass, title);
        wenzhangArticle.setStatus(1);
        wenzhangArticleRepository.save(wenzhangArticle);
    }

    @Override
    public void RejectArticle(String mass, String title) {
        WenzhangArticle wenzhangArticle = wenzhangArticleRepository.findByMassAndTitle(mass, title);
        wenzhangArticle.setStatus(2);
        wenzhangArticleRepository.save(wenzhangArticle);
    }

    @Override
    public List<WenzhangArticle> GetAllArticle() {
        List<WenzhangArticle> wenzhangArticles = wenzhangArticleRepository.findAll();
        return wenzhangArticles;
    }

}
