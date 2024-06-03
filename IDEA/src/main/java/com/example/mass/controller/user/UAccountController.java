package com.example.mass.controller.user;

import com.example.mass.server.user.UAccountServer;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-account")
@Api(value = "Article Controller", description = "用户系统的账号接口")
public class UAccountController {
    private final UAccountServer accountServer;

    public UAccountController(UAccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestParam String sno, @RequestParam String pwd) {
        if(accountServer.Register(sno, pwd)) {
            return new ResponseEntity<>("Register success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Account have exited", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestParam String sno, @RequestParam String pwd) {
        String temp = accountServer.Login(sno, pwd);
        if(temp == "Account is not found or pwd is wrong") {
            return new ResponseEntity<>("Account is not found or pwd is wrong", HttpStatus.OK);
        }
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> Logout(@RequestParam String sno) {
        accountServer.Logout(sno);
        return new ResponseEntity<>("Logout success", HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> Verify(@RequestParam String sno, @RequestParam String token) {
        if(accountServer.VerifyLogin(sno, token)) {
            return new ResponseEntity<>("Valid identity", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid identity", HttpStatus.OK);
    }

//    @PostMapping("/isPerfect")
//    public ResponseEntity<String> isPerfect(@RequestParam String sno) {
//        if(accountServer.isPerfect(sno)) {
//            return new ResponseEntity<>("IsPerfect", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Is not perfect", HttpStatus.OK);
//    }

    @PostMapping("/perfect")
    public ResponseEntity<String> Perfect(@RequestBody Info info) {
        accountServer.perfectInfo(info.getSno(), info.getName(), info.getSex(), info.getBelong(), info.getMail());
        return new ResponseEntity<>("Perfect success", HttpStatus.OK);
    }

    @PostMapping("info/get")
    public ResponseEntity<?> getInfo(@RequestParam String sno) {
        return new ResponseEntity<>(accountServer.getInfo(sno), HttpStatus.OK);
    }

    @PostMapping("/update/info")
    public ResponseEntity<String> UpdateInfo(@RequestBody Info info) {
        accountServer.updateInfo(info.getSno(), info.getName(), info.getBelong(), info.getMail());
        return new ResponseEntity<>("Update info success", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> Delete(@RequestParam String sno) {
        accountServer.Delete(sno);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("/update/pwd")
    public ResponseEntity<String> UpdatePwd(@RequestParam String sno, @RequestParam String old, @RequestParam String nuw) {
        if(accountServer.UpdatePwd(sno, old, nuw)) {
            return new ResponseEntity<>("Update pwd success", HttpStatus.OK);
        }
        return new ResponseEntity<>("old pwd is wrong", HttpStatus.OK);
    }

    public static class Info {
        private String sno;
        private String name;
        private String sex;
        private String belong;
        private String mail;

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
    }


}
