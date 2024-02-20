package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.HoutaiAccount;
import cn.ganxy03.xh_website.config.RedisUtil;
import cn.ganxy03.xh_website.service.HoutaiAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("houtai-account")
public class HoutaiAccountController {

    private final HoutaiAccountService houtaiAccountService;
    private final RedisUtil redisUtil;

    private HoutaiAccountController(HoutaiAccountService houtaiAccountService, RedisUtil redisUtil) {
        this.houtaiAccountService = houtaiAccountService;
        this.redisUtil = redisUtil;
    }

    @PostMapping("account-create")
    public ResponseEntity<String> CreateAccount(@RequestParam String role) {
        houtaiAccountService.CreateAccount(role);
        return new ResponseEntity<>("Account created", HttpStatus.OK);
    }

    @PostMapping("account-changeRole")
    public ResponseEntity<String> ChangeRole(@RequestParam String account, @RequestParam String role) {
        houtaiAccountService.ChangeRole(account, role);
        return new ResponseEntity<>("Role created", HttpStatus.OK);
    }

    @PostMapping("account-delete")
    public ResponseEntity<String> DeleteAccount(@RequestParam String account) {
        houtaiAccountService.DeleteAccount(account);
        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }

    @PostMapping("account-initPwd")
    public ResponseEntity<String> InitPwd(@RequestParam String account) {
        houtaiAccountService.InitPwd(account);
        return new ResponseEntity<>("Pwd init", HttpStatus.OK);
    }

    @PostMapping("account-changePwd")
    public ResponseEntity<String> ChangePwd(@RequestParam String account, @RequestParam String pwd) {
        houtaiAccountService.ChangePwd(account, pwd);
        return new ResponseEntity<>("Pwd changed", HttpStatus.OK);
    }

    @PostMapping("account-login")
    public ResponseEntity<String> LoginAccount(@RequestParam String account, @RequestParam String pwd, HttpServletRequest request) {
        Integer isLogin = houtaiAccountService.Login(account, pwd, request);
        if (isLogin == 1) {
            return new ResponseEntity<>("Account is deleted", HttpStatus.BAD_REQUEST);
        } else if (isLogin == 2) {
            redisUtil.selectDatabase(3);
            String token = redisUtil.generateToken(account);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found or pwd is wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("account-logout")
    public ResponseEntity<String> LogoutAccount(@RequestParam String account) {
        redisUtil.selectDatabase(3);
        redisUtil.deleteToken(account);
        return new ResponseEntity<>("Account logout", HttpStatus.OK);
    }

    @PostMapping("account-verifyLogin")
    public ResponseEntity<String> VerifyLogin(@RequestParam String account, @RequestParam String code) {
        redisUtil.selectDatabase(3);
        String storeCode = redisUtil.get(account);
        if(storeCode != null && storeCode.equals(code)) {
            return new ResponseEntity<>("Verify success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found or code is wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("account-getInfo")
    public ResponseEntity<HoutaiAccount> GetInfo(@RequestParam String account) {
        HoutaiAccount houtaiAccount = houtaiAccountService.GetInfo(account);
        return new ResponseEntity<>(houtaiAccount, HttpStatus.OK);
    }
}
