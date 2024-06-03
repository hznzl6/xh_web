package com.example.mass.controller;

import com.example.mass.config.RedisUtil;
import com.example.mass.server.EmailService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@Api(value = "Article Controller", description = "邮箱服务的接口")
@SuppressWarnings("ClassCanBeRecord")
public class MailController {
    private final EmailService emailService;
    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/send-mail")
    public ResponseEntity<String> sendCode(@RequestParam String email) {
        try {
            emailService.sendVerifyCode(email);
            return ResponseEntity.ok("Send success.");
        } catch (Exception e) {
            return ResponseEntity.ok("Send failed.");
        }
    }

    @PostMapping("/verify-mail")
    public ResponseEntity<String> VerificationCode(@RequestParam String email, @RequestParam String code) {
        RedisUtil.selectDatabase(6);
        String storedCode = RedisUtil.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            return ResponseEntity.ok("Verification success");
        } else {
            return ResponseEntity.ok("Verification failed.");
        }
    }
}
