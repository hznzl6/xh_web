package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanLogin;
import cn.ganxy03.xh_website.config.RedisUtil;
import cn.ganxy03.xh_website.repository.ShetuanLoginRepository;
import cn.ganxy03.xh_website.service.EmailService;
import cn.ganxy03.xh_website.service.ShetuanLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/shetuan")
public class ShetuanLoginController {
    private final ShetuanLoginService shetuanLoginService;
    private final RedisUtil redisUtil;
    private final EmailService emailService;
    private final ShetuanLoginRepository shetuanLoginRepository;

    @Autowired
    public ShetuanLoginController(ShetuanLoginService shetuanLoginService, RedisUtil redisUtil, EmailService emailService, ShetuanLoginRepository shetuanLoginRepository) {
        this.shetuanLoginService = shetuanLoginService;
        this.redisUtil = redisUtil;
        this.emailService = emailService;
        this.shetuanLoginRepository = shetuanLoginRepository;
    }

    @PostMapping("/registerByMail")
    public ResponseEntity<String> RegisterByMail(@RequestBody LoginRequest loginRequest){
        boolean isExist = shetuanLoginRepository.existsBySno(loginRequest.getSno());
        if (isExist) {
            return new ResponseEntity<>("The sno already exists!", HttpStatus.BAD_REQUEST);
        }
        shetuanLoginService.RegisterByMail(loginRequest.getSno(), loginRequest.getPwd(), loginRequest.getMail());
        return new ResponseEntity<>("Register success!",HttpStatus.OK);
    }

    @PostMapping("/registerByPhone")
    public ResponseEntity<String> RegisterByPhone(@RequestBody LoginRequest2 loginRequest2){
        boolean isExist = shetuanLoginRepository.existsBySno(loginRequest2.getSno());
        if (isExist) {
            return new ResponseEntity<>("The sno already exists!", HttpStatus.BAD_REQUEST);
        }
        shetuanLoginService.RegisterByPhone(loginRequest2.getSno(), loginRequest2.getPwd(), loginRequest2.getPhone());
        return new ResponseEntity<>("Register success!",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String sno, @RequestParam String pwd, HttpServletRequest request) {
        boolean loginResult = shetuanLoginService.login(sno, pwd, request);
        if(loginResult) {
            String token = redisUtil.generateToken(sno);
            redisUtil.saveToken(sno, token);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("sorry, u pwd is wrong!");
    }
    @PutMapping("/update-pwd")
    public ResponseEntity<?> updatePassword(@RequestParam String sno, @RequestParam String newPwd) {
        boolean isUpdated = shetuanLoginService.updatePassword(sno, newPwd);
        if (isUpdated) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password");
        }
    }
    @PostMapping("/getInfo")
    public ResponseEntity<?> getInfo(@RequestParam String sno) {
        ShetuanLogin shetuanLogin = shetuanLoginService.getInfoBySno(sno);
        if(shetuanLogin != null) {
            return new ResponseEntity<>(shetuanLogin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sno not found", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String sno) {
        redisUtil.deleteToken(sno);
        return new ResponseEntity<>("Logout success!", HttpStatus.OK);
    }

    @PostMapping("/verification-login")
    public ResponseEntity<String> verifyLogin(@RequestParam String sno, @RequestParam String token) {
        String storedToken = redisUtil.getToken(sno);
        if (storedToken != null && storedToken.equals(token)) {
            return new ResponseEntity<>("Verification success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Token not found!", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/send-mail")
    public ResponseEntity<String> sendCode(@RequestParam String email) {
        try {
            emailService.sendVerificationCode(email);
            return ResponseEntity.ok("Send success.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Send failed.");
        }
    }

    @PostMapping("/verification-mail")
    public ResponseEntity<String> VerificationCode(@RequestParam String email, @RequestParam String code) {
        String storedCode = RedisUtil.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            return ResponseEntity.ok("Verification success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification failed.");
        }
    }

    @PostMapping("/verification-perfect")
    public ResponseEntity<String> VerificationPerfect(@RequestParam String sno) {
        ShetuanLogin shetuanLogin = shetuanLoginService.getInfoBySno(sno);
        if (shetuanLogin != null && shetuanLogin.getPerfect() == 1) {
            return ResponseEntity.ok("Verification perfection");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification imperfection.");
        }
    }
    public static class LoginRequest2 {
        private String sno;
        private String pwd;
        private String phone;

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
    public static class LoginRequest {
        private String sno;
        private String pwd;
        private String mail;
        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
    }

}
