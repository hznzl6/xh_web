package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.WenzhangArticle;
import cn.ganxy03.xh_website.service.WenzhangArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wenzhang-artile")
public class WenzhangArticleController {
    private final WenzhangArticleService wenzhangArticleService;

    public WenzhangArticleController(WenzhangArticleService wenzhangArticleService) {
        this.wenzhangArticleService = wenzhangArticleService;
    }

    @PostMapping("post-article")
    public ResponseEntity<String> PostArticle(@RequestBody PostArticle postArticle) {
        boolean isPost = wenzhangArticleService.PostArticle(postArticle.getMass(), postArticle.getTitle(), postArticle.getContent(), postArticle.getAuthor());
        if (isPost) {
            return new ResponseEntity<>("Post success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update success", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("agree-article")
    public ResponseEntity<String> AgreeArticle(@RequestParam String mass, @RequestParam String title) {
        wenzhangArticleService.AgreeArticle(mass, title);
        return new ResponseEntity<>("Agree success", HttpStatus.OK);
    }

    @PostMapping("reject-article")
    public ResponseEntity<String> RejectArticle(@RequestParam String mass, @RequestParam String title) {
        wenzhangArticleService.RejectArticle(mass, title);
        return new ResponseEntity<>("Reject success", HttpStatus.OK);
    }


    @PostMapping("delete-article")
    public ResponseEntity<String> DeleteArticle(@RequestParam String mass, @RequestParam String title) {
        boolean isDelete = wenzhangArticleService.DeleteArticle(mass, title);
        if (isDelete) {
            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Article not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("getAll")
    public ResponseEntity<List> GetAll() {
        List<WenzhangArticle> wenzhangArticles = wenzhangArticleService.GetAllArticle();
        return new ResponseEntity<>(wenzhangArticles, HttpStatus.OK);
    }

    @PostMapping("getAll-ByMass")
    public ResponseEntity<List> GetAllOnMass(@RequestParam String mass) {
        List<WenzhangArticle> wenzhangArticles = wenzhangArticleService.GetAllArticleByMass(mass);
        return new ResponseEntity<>(wenzhangArticles, HttpStatus.OK);
    }

    public static class PostArticle {
        private String mass;
        private String title;
        private String content;
        private String author;

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

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
