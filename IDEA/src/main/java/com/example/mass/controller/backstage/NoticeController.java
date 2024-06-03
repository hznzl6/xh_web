package com.example.mass.controller.backstage;

import com.example.mass.server.backstage.NoticeServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("backstage-notice")
public class NoticeController {
    private final NoticeServer noticeServer;

    public NoticeController(NoticeServer noticeServer) {
        this.noticeServer = noticeServer;
    }

    @PostMapping("/post")
    public ResponseEntity<String> Post(@RequestBody Notice notice) {
        if(noticeServer.PostNotice(notice.getTitle(), notice.getContent(), notice.getPoster())) {
            return new ResponseEntity<>("Post success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Notice have exited", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> Delete(@RequestParam String title) {
        noticeServer.DeleteNotice(title);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("/getNotices")
    public ResponseEntity<List> getNotices() {
        return new ResponseEntity<>(noticeServer.getNotices(), HttpStatus.OK);
    }
    public static class Notice {
        private String title;
        private String content;
        private String poster;

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

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }
    }
}
