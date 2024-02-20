package cn.ganxy03.xh_website.controller;


import cn.ganxy03.xh_website.antity.UserSecure;
import cn.ganxy03.xh_website.service.UserSecureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-secure")
public class UserSecureController {
    private final UserSecureService userSecureService;

    public UserSecureController(UserSecureService userSecureService) {
        this.userSecureService = userSecureService;
    }

    @PostMapping("/getAllBySno")
    public ResponseEntity<?> GetAllBySno(@RequestParam String sno) {
        List<UserSecure> userSecures = userSecureService.GetAllBySno(sno);
        if(userSecures != null) {
            return new ResponseEntity<>(userSecures, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sno not found", HttpStatus.BAD_REQUEST);
        }
    }
}
