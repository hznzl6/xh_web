package com.example.mass.controller.backstage;

import com.example.mass.server.backstage.BelongServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("backstage-belong")
public class BelongController {
    private final BelongServer belongServer;

    public BelongController(BelongServer belongServer) {
        this.belongServer = belongServer;
    }

    @PostMapping("/new")
    public ResponseEntity<String> newOne(@RequestParam String period, @RequestParam String belong) {
        if(belongServer.newOne(period, belong)) {
            return new ResponseEntity<>("Create success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Belong have exited", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteOne(@RequestParam String belong) {
        belongServer.deleteOne(belong);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("/disable")
    public ResponseEntity<String> disableOne(@RequestParam String belong) {
        belongServer.disableOne(belong);
        return new ResponseEntity<>("Disable success", HttpStatus.OK);
    }

    @PostMapping("getAll")
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(belongServer.getBelongs(), HttpStatus.OK);
    }
}
