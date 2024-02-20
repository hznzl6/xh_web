package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanBroadcast;
import cn.ganxy03.xh_website.service.ShetuanBroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shetuan-broadcast")
public class ShetuanBroadcastController {
    private final ShetuanBroadcastService shetuanBroadcastService;

    @Autowired
    public ShetuanBroadcastController(ShetuanBroadcastService shetuanBroadcastService) {
        this.shetuanBroadcastService = shetuanBroadcastService;
    }

    @PostMapping("post-broadcast")
    public ResponseEntity<String> PostBroadcast(@RequestBody PostBroadcast postBroadcast) {
        boolean isPost = shetuanBroadcastService.PostBroadcast(postBroadcast.getMass(), postBroadcast.getTitle(), postBroadcast.getContent());
        if (isPost) {
            return new ResponseEntity<>("Post success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post is exited", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update-broadcast")
    public ResponseEntity<String> UpdateBroadcast(@RequestBody PostBroadcast postBroadcast) {
        boolean isUpdate = shetuanBroadcastService.UpdateBroadcast(postBroadcast.getMass(), postBroadcast.getTitle(), postBroadcast.getContent());
        if (isUpdate) {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Broadcast not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("getAll-broadcast")
    public ResponseEntity<List> GetAllBroadcast(@RequestParam String mass) {
        List<ShetuanBroadcast> shetuanBroadcasts = shetuanBroadcastService.GetAllBroadcast(mass);
        return new ResponseEntity<>(shetuanBroadcasts, HttpStatus.OK);
    }

    @PostMapping("delete-broadcast")
    public ResponseEntity<String> DeleteBroadcast(@RequestParam String mass, @RequestParam String title) {
        boolean isDelete = shetuanBroadcastService.DeleteBroadcast(mass, title);
        if (isDelete) {
            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Broadcast not found", HttpStatus.BAD_REQUEST);
        }
    }

    public static class PostBroadcast {
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

}
