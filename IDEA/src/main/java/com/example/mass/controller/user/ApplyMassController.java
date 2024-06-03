package com.example.mass.controller.user;

import com.example.mass.server.user.ApplyMassServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-applyMass")
public class ApplyMassController {
    private final ApplyMassServer applyMassServer;

    public ApplyMassController(ApplyMassServer applyMassServer) {
        this.applyMassServer = applyMassServer;
    }

    @PostMapping("/getApplies")
    public ResponseEntity<List> getApplies(@RequestParam String mass) {
        return new ResponseEntity<>(applyMassServer.getApplies(mass), HttpStatus.OK);
    }

    @PostMapping("/agree")
    public ResponseEntity<String> agreeJoin(@RequestParam String mass,@RequestParam String depart, @RequestParam String sno) {
        applyMassServer.AgreeJoin(mass, depart, sno);
        return new ResponseEntity<>("Agree success", HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity<String> rejectJoin(@RequestParam String mass, @RequestParam String sno) {
        applyMassServer.RejectJoin(mass, sno);
        return new ResponseEntity<>("Reject success", HttpStatus.OK);
    }
}
