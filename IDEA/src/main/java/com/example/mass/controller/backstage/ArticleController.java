package com.example.mass.controller.backstage;

import com.example.mass.entity.backstage.Article;
import com.example.mass.server.backstage.ArticleServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("backstage-article")
public class ArticleController {

    private final ArticleServer articleServer;

    public ArticleController(ArticleServer articleServer) {
        this.articleServer = articleServer;
    }

    @PostMapping("pass")
    public ResponseEntity<String> Pass(@RequestParam String mass, @RequestParam String title) {
        articleServer.PassArticle(mass, title);
        return new ResponseEntity<>("Pass success", HttpStatus.OK);
    }

    @PostMapping("reject")
    public ResponseEntity<String> Reject(@RequestParam String mass, @RequestParam String title) {
        articleServer.RejectArticle(mass, title);
        return new ResponseEntity<>("Reject success", HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(articleServer.getAll(), HttpStatus.OK);
    }

    @PostMapping("/getOne")
    public ResponseEntity<?> getOne(@RequestParam String mass, @RequestParam String title) {
        return new ResponseEntity<>(articleServer.getOne(mass, title), HttpStatus.OK);
    }
}
