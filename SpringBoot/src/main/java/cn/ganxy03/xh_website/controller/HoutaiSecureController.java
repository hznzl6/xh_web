package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.HoutaiSecure;
import cn.ganxy03.xh_website.service.HoutaiSecureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("houtai-secure")
public class HoutaiSecureController {
    private HoutaiSecureService houtaiSecureService;

    public HoutaiSecureController(HoutaiSecureService houtaiSecureService) {
        this.houtaiSecureService = houtaiSecureService;
    }

    @PostMapping("secure-getAll")
    public ResponseEntity<List> SecureGetAll(@RequestParam String account) {
        List<HoutaiSecure> houtaiSecures = houtaiSecureService.GetAllByAccount(account);
        return new ResponseEntity<>(houtaiSecures, HttpStatus.OK);
    }


}
