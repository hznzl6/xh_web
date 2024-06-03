package com.example.mass.controller.manager;

import com.example.mass.entity.manager.Member;
import com.example.mass.entity.user.Info;
import com.example.mass.server.manager.MemberServer;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("manager-member")
@Api(value = "Article Controller", description = "社团管理系统的成员管理接口")
public class MemberController {
    private final MemberServer memberServer;

    public MemberController(MemberServer memberServer) {
        this.memberServer = memberServer;
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateMember(@RequestParam String sno, @RequestParam String name, @RequestParam String sex, @RequestParam String belong, @RequestParam String mail) {
        memberServer.UpdateOne(sno, name, sex, belong, mail);
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @PostMapping("getOne")
    public ResponseEntity<Info> getOne(@RequestParam String sno) {
        return new ResponseEntity<>(memberServer.GetOne(sno), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteOne(@RequestParam String mass,@RequestParam String depart, @RequestParam String sno) {
        memberServer.DeleteOne(mass, depart, sno);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("/getMembers")
    public ResponseEntity<List> getMembers(@RequestParam String mass) {
        return new ResponseEntity<>(memberServer.getMembers(mass), HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(memberServer.getAll(), HttpStatus.OK);
    }

}
