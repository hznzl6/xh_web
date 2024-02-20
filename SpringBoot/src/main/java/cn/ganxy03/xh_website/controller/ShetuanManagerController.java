package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanManager;
import cn.ganxy03.xh_website.config.RedisUtil;
import cn.ganxy03.xh_website.service.EmailService;
import cn.ganxy03.xh_website.service.ShetuanManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shetuan-manager")
public class ShetuanManagerController {
    private final ShetuanManagerService shetuanManagerService;
    private final RedisUtil redisUtil;
    private final EmailService emailService;

    public ShetuanManagerController(ShetuanManagerService shetuanManagerService, RedisUtil redisUtil, EmailService emailService) {
        this.shetuanManagerService = shetuanManagerService;
        this.redisUtil = redisUtil;
        this.emailService = emailService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> GenerateAccount(@RequestParam String mass) {
        boolean isGenerate = shetuanManagerService.GenerateAccount(mass);
        if(isGenerate) {
            return new ResponseEntity<>("Generate success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mass not fount", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update-pwd")
    public ResponseEntity<String> UpdatePwd(@RequestParam String account, @RequestParam String pwd) {
        boolean isUpdate = shetuanManagerService.Updatepwd(account, pwd);
        if(isUpdate) {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not fount", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update-info")
    public ResponseEntity<String> UpdateInfo(@RequestBody ManagerInfo managerInfo) {
        boolean isUpdate = shetuanManagerService.UpdateInfo(managerInfo.getAccount(), managerInfo.getMail(), managerInfo.getQq(), managerInfo.getPhone());
        if(isUpdate) {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("manager-login")
    public ResponseEntity<String> ManagerLogin(@RequestParam String account, @RequestParam String pwd) {
        boolean islogin = shetuanManagerService.Login(account, pwd);
        if(islogin) {
            String token = redisUtil.generateToken(account);
            redisUtil.saveToken(account, token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found or pwd is wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("manager-verifyLogin")
    public ResponseEntity<String> ManagerVerifyLogin(@RequestParam String account, @RequestParam String token) {
        String storedToken = redisUtil.getToken(account);
        if (storedToken != null && storedToken.equals(token)) {
            return new ResponseEntity<>("Verification success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Token not found!", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("manager-getInfo")
    public ResponseEntity<ShetuanManager> GetManagerInfo(@RequestParam String account) {
        ShetuanManager shetuanManager = shetuanManagerService.GetInfo(account);
        return new ResponseEntity<>(shetuanManager, HttpStatus.OK);
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

    @PostMapping("ReturnMass")
    public ResponseEntity<String> ReturnMass(@RequestParam String account) {
        String Mass = shetuanManagerService.ReturnMass(account);
        return new ResponseEntity<>(Mass, HttpStatus.OK);
    }

    public static class ManagerInfo {
        private String account;
        private String mail;
        private String qq;
        private String phone;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
