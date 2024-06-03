package com.example.mass.controller.manager;

import com.example.mass.server.manager.MAccountServer;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager-account")
@Api(value = "Article Controller", description = "社团管理系统的账号接口")
public class MAccountController {
    private final MAccountServer accountServer;

    public MAccountController(MAccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestParam String account, @RequestParam String pwd) {
        String temp = accountServer.Login(account, pwd);
        if(temp == "Account is not found or pwd is wrong") {
            return new ResponseEntity<>("Account is not found or pwd is wrong", HttpStatus.OK);
        }
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> Logout(@RequestParam String account) {
        accountServer.Logout(account);
        return new ResponseEntity<>("Logout success", HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> Verify(@RequestParam String account, @RequestParam String token) {
        if(accountServer.VerifyLogin(account, token)) {
            return new ResponseEntity<>("Valid identity", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid identity", HttpStatus.OK);
    }

    @PostMapping("/update/pwd")
    public ResponseEntity<String> UpdatePwd(@RequestParam String account, @RequestParam String old, @RequestParam String nuw) {
        if(accountServer.UpdatePwd(account, old, nuw)) {
            return new ResponseEntity<>("Update pwd success", HttpStatus.OK);
        }
        return new ResponseEntity<>("old pwd is wrong", HttpStatus.OK);
    }
}
