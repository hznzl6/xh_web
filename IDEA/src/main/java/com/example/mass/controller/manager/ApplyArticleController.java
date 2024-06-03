package com.example.mass.controller.manager;

import com.example.mass.server.manager.ApplyArticleServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager-article")
@Api(value = "Article Controller", description = "社团管理系统的文章发布接口")
public class ApplyArticleController {
    private final ApplyArticleServer articleServer;

    public ApplyArticleController(ApplyArticleServer articleServer) {
        this.articleServer = articleServer;
    }

    @PostMapping("/post")
    public ResponseEntity<String> postArticle(@RequestBody Article article) {
        if(articleServer.postArticle(article.getMass(), article.getTitle(), article.getContent(), article.getSno())) {
            return new ResponseEntity<>("Post success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Article have exited", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody UpdateArticle updateArticle) {
        articleServer.updateArticle(updateArticle.getMass(), updateArticle.getTitle(), updateArticle.getContent());
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteArticle(@RequestParam String mass, @RequestParam String title) {
        articleServer.deleteArticle(mass, title);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("/getArticles")
    public ResponseEntity<List> getArticles(@RequestParam String mass) {
        return new ResponseEntity<>(articleServer.getArticles(mass), HttpStatus.OK);
    }

    public static class UpdateArticle {
        private String mass;
        private String title;
        private String content;

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
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

    public static class Article {
        private String mass;
        private String title;
        private String content;
        private String sno;

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
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

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }
    }
}
