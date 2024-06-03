package com.example.mass.controller.backstage;

import com.example.mass.server.backstage.ActivityServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backstage-activity")
public class ActivityController {
    private final ActivityServer activityServer;
    public ActivityController(ActivityServer activityServer) {
        this.activityServer = activityServer;
    }

    @PostMapping("/pass")
    public ResponseEntity<String> PassActivity(@RequestParam String mass, @RequestParam String title) {
        activityServer.PassActivity(mass, title);
        return new ResponseEntity<>("Pass success", HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity<String> RejectActivity(@RequestParam String mass, @RequestParam String title) {
        activityServer.RejectActivity(mass, title);
        return new ResponseEntity<>("Reject success", HttpStatus.OK);
    }

    @PostMapping("/getActivities")
    public ResponseEntity<?> getActivities(@RequestParam String mass) {
        return new ResponseEntity<>(activityServer.getAll(mass), HttpStatus.OK);
    }
}
