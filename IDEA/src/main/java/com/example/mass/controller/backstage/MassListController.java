package com.example.mass.controller.backstage;

import com.example.mass.server.backstage.MassListServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("backstage-massList")
public class MassListController {
    private final MassListServer massListServer;

    public MassListController(MassListServer massListServer) {
        this.massListServer = massListServer;
    }

    @PostMapping("/newOne")
    public ResponseEntity<String> newOne(@RequestParam String mass) {
        if(massListServer.NewOne(mass)) {
            return new ResponseEntity<>("Create success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Mass have exited", HttpStatus.OK);
    }

    @PostMapping("/getOne")
    public ResponseEntity<?> getOne(@RequestParam String mass) {
        return new ResponseEntity<>(massListServer.GetOne(mass), HttpStatus.OK);
    }

    @PostMapping("/updateOne")
    public ResponseEntity<String> UpdateOne(@RequestParam String mass, @RequestParam String manager, @RequestParam String slogan, @RequestParam String teacher) {
        massListServer.UpdateOne(mass, manager, slogan, teacher);
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @PostMapping("/updateInfo")
    public ResponseEntity<String> UpdateOne(@RequestBody UpdateInfo updateInfo) {
        massListServer.UpdateInfo(updateInfo.getMass(), updateInfo.getInfo());
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @PostMapping("/deleteOne")
    public ResponseEntity<String> DeleteOne(@RequestParam String mass) {
        massListServer.DeleteOne(mass);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(massListServer.getAll(), HttpStatus.OK);
    }


    public static class UpdateInfo {
        private String mass;
        private String info;

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
