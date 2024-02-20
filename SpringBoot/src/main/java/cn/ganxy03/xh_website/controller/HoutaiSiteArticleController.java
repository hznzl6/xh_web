package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.HoutaiSite;
import cn.ganxy03.xh_website.service.HoutaiSiteArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("houtai-siteArticle")
public class HoutaiSiteArticleController {
    private HoutaiSiteArticleService houtaiSiteArticleService;
    public HoutaiSiteArticleController(HoutaiSiteArticleService houtaiSiteArticleService) {
        this.houtaiSiteArticleService = houtaiSiteArticleService;
    }

    @PostMapping("/siteArticle-save")
    public ResponseEntity<String> SaveArticle(@RequestBody SiteArticle siteArticle) {
        boolean isSave = houtaiSiteArticleService.SaveArticle(siteArticle.getBelong(), siteArticle.getTitle(), siteArticle.getContent());
        if(isSave) {
            return new ResponseEntity<>("Save success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        }
    }

    @PostMapping("/siteArticle-getArticle")
    public ResponseEntity<HoutaiSite> GetArticle(@RequestParam String belong, @RequestParam String title) {
        HoutaiSite houtaiSite = houtaiSiteArticleService.GetArticle(belong, title);
        if(houtaiSite != null) {
            return new ResponseEntity<>(houtaiSite, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/siteArticle-getAllArticle")
    public ResponseEntity<List> GetAllSites() {
        List<HoutaiSite> houtaiSites = houtaiSiteArticleService.GetAllSites();
        return new ResponseEntity<>(houtaiSites, HttpStatus.OK);
    }

    public static class SiteArticle {
        private String belong;
        private String title;
        private String content;

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
