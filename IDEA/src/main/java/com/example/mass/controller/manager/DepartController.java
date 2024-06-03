package com.example.mass.controller.manager;

import com.example.mass.server.manager.DepartServer;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("manager-department")
@Api(value = "Article Controller", description = "社团管理系统的部门接口")
public class DepartController {
    private final DepartServer departServer;

    public DepartController(DepartServer departServer) {
        this.departServer = departServer;
    }

    @PostMapping("/new")
    public ResponseEntity<String> newOne(@RequestParam String mass, @RequestParam String depart) {
        if(departServer.newDepart(mass, depart)) {
            return new ResponseEntity<>("Create success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Depart have exited", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteOne(@RequestParam String mass, @RequestParam String depart) {
        departServer.delDepart(mass, depart);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("/getDeparts")
    public ResponseEntity<List> getDeparts(@RequestParam String mass) {
        return new ResponseEntity<>(departServer.getDepart(mass), HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(departServer.getAll(), HttpStatus.OK);
    }
}
