package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.WenzhangArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WenzhangArticleRepository extends JpaRepository<WenzhangArticle, Integer> {

    WenzhangArticle findByMassAndTitleAndStatus(String mass, String title, Integer status);

    WenzhangArticle findByMassAndTitle(String mass, String title);

    List<WenzhangArticle> findAllByMassAndStatus(String mass, Integer status);

    List<WenzhangArticle> findAllByMass(String mass);

    List<WenzhangArticle> findAll();
}
