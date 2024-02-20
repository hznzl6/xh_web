package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.GonggaoNotice;
import cn.ganxy03.xh_website.service.GonggaoNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gonggao-notice")
public class GonggaoNoticeController {
    private final GonggaoNoticeService gonggaoNoticeService;

    public GonggaoNoticeController(GonggaoNoticeService gonggaoNoticeService) {
        this.gonggaoNoticeService = gonggaoNoticeService;
    }

    @PostMapping("notice-post")
    public ResponseEntity<String> PostNotice(@RequestBody PostNotice postNotice) {
        boolean isPost = gonggaoNoticeService.PostNotice(postNotice.getTitle(), postNotice.getContent(), postNotice.getPublisher());
        if (isPost) {
            return new ResponseEntity<>("Post success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        }
    }

    @PostMapping("notice-getTitle")
    public ResponseEntity<List<Map<String, Object>>> GetNoticeTitle() {
        List<Map<String, Object>> titleList = gonggaoNoticeService.GetTitle();
        return ResponseEntity.ok(titleList);
    }

    @PostMapping("notice-getContent")
    public ResponseEntity<GonggaoNotice> GetNoticeContent(@RequestParam String title) {
        GonggaoNotice gonggaoNotice = gonggaoNoticeService.GetContentByTitle(title);
//        return ResponseEntity.ok(gonggaoNotice);
        return new ResponseEntity<>(gonggaoNotice, HttpStatus.OK);
    }

    @PostMapping("notice-hits")
    public ResponseEntity<String> Hits(@RequestParam String title) {
        gonggaoNoticeService.Hits(title);
        return new ResponseEntity<>("Hits success", HttpStatus.OK);
    }



    public static class PostNotice {
        private String title;
        private String content;
        private String publisher;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }
    }
}
