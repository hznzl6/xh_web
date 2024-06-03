package com.example.mass.controller.user;

import com.example.mass.server.user.UApplyActivityServer;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-applyActivity")
@Api(value = "Article Controller", description = "用户系统的活动申请接口")
public class UApplyActivityController {
    private final UApplyActivityServer uApplyActivityServer;

    public UApplyActivityController(UApplyActivityServer uApplyActivityServer) {
        this.uApplyActivityServer = uApplyActivityServer;
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyJoin(@RequestParam Integer target, @RequestParam String mass, @RequestParam String sno) {
        if(uApplyActivityServer.applyJoin(target, mass, sno)) {
            return new ResponseEntity<>("Apply success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Have Applied", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteApply(@RequestParam Integer target, @RequestParam String sno) {
        uApplyActivityServer.deleteApply(target, sno);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("getActivities")
    public ResponseEntity<List> getActivities(@RequestParam String mass) {
        return new ResponseEntity<>(uApplyActivityServer.getActivities(mass), HttpStatus.OK);
    }

    @PostMapping("/getJoinActivity")
    public ResponseEntity<List> getJoinActivity(@RequestParam String mass, @RequestParam String sno) {
        return new ResponseEntity<>(uApplyActivityServer.getJoinActivities(mass, sno), HttpStatus.OK);
    }
}
