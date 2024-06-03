package com.example.mass.controller.manager;

import com.example.mass.entity.manager.Broadcast;
import com.example.mass.server.manager.BroadcastServer;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager-broadcast")
@Api(value = "Article Controller", description = "社团管理系统的广播发布接口")
public class BroadcastController {
    private final BroadcastServer broadcastServer;

    public BroadcastController(BroadcastServer broadcastServer) {
        this.broadcastServer = broadcastServer;
    }

    @PostMapping("/post")
    public ResponseEntity<String> postBroadcast(@RequestBody BroadCast broadCast) {
        if(broadcastServer.postBroadcast(broadCast.getMass(), broadCast.getTitle(), broadCast.getContent(), broadCast.getSno())) {
            return new ResponseEntity<>("Post success", HttpStatus.OK);
        }
        return new ResponseEntity<>("BroadCast have exited", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBroadcast(@RequestBody UpdateCast updateCast) {
        broadcastServer.updateBroadcast(updateCast.getMass(), updateCast.getTitle(), updateCast.getContent());
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteBroadcast(@RequestParam String mass, @RequestParam String title) {
        broadcastServer.deleteBroadcast(mass, title);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("/getBroadcasts")
    public ResponseEntity<List> getBroadcasts(@RequestParam String mass) {
        return new ResponseEntity<>(broadcastServer.getBroadcasts(mass), HttpStatus.OK);
    }
    public static class UpdateCast {
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
    public static class BroadCast {
        private String mass;
        private String sno;
        private String title;
        private String content;

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
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
