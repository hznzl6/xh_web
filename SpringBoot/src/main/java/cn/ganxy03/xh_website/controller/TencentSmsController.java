package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.service.TencentSmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Tencent-sms")
public class TencentSmsController {

    private final TencentSmsService tencentSmsService;
    public TencentSmsController(TencentSmsService tencentSmsService) {
        this.tencentSmsService = tencentSmsService;
    }

    @PostMapping("Send-sms")
    public ResponseEntity<String> SendSms(@RequestParam String phone) {
        boolean isSend = tencentSmsService.sendSms(phone);
        if(isSend) {
            return new ResponseEntity<>("Send success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Send failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("Verify-sms")
    public ResponseEntity<String> VerifySms(@RequestParam String phone, @RequestParam String code) {
        boolean isSend = tencentSmsService.verifyCode(phone, code);
        if(isSend) {
            return new ResponseEntity<>("Verify success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Verify failed", HttpStatus.BAD_REQUEST);
        }
    }
}
