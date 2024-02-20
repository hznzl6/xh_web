package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanLogin;
import cn.ganxy03.xh_website.antity.ShetuanUser;
import cn.ganxy03.xh_website.repository.ShetuanLoginRepository;
import cn.ganxy03.xh_website.repository.ShetuanUserRepository;
import cn.ganxy03.xh_website.service.ShetuanLoginService;
import cn.ganxy03.xh_website.service.ShetuanUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shetuan-user")
public class ShetuanUserController {
    private final ShetuanUserService shetuanUserService;
    private final ShetuanLoginService shetuanLoginService;
    private final ShetuanLoginRepository shetuanLoginRepository;
    private final ShetuanUserRepository shetuanUserRepository;
    @Autowired
    public ShetuanUserController(ShetuanUserService shetuanUserService, ShetuanLoginService shetuanLoginService, ShetuanLoginRepository shetuanLoginRepository, ShetuanUserRepository shetuanUserRepository) {
        this.shetuanUserService = shetuanUserService;
        this.shetuanLoginService = shetuanLoginService;
        this.shetuanLoginRepository = shetuanLoginRepository;
        this.shetuanUserRepository = shetuanUserRepository;
    }
    @PostMapping("/perfect")
    public ResponseEntity<String> Perfect(@RequestBody PerfectRequest perfectRequest){
        boolean isExist = shetuanUserRepository.existsBySno(perfectRequest.getSno());
        if(isExist) {
            return new ResponseEntity<>("Have perfected!", HttpStatus.BAD_REQUEST);
        }
        boolean isPerfect = shetuanUserService.Perfect(perfectRequest.getSno(), perfectRequest.getNickname(), perfectRequest.getName(), perfectRequest.getBelong(), perfectRequest.getQq(), perfectRequest.getMail(), perfectRequest.getPhone());
        if(isPerfect) {
            ShetuanLogin shetuanLogin = shetuanLoginService.getInfoBySno(perfectRequest.getSno());
            if(shetuanLogin != null && shetuanLogin.getPerfect() == 0) {
                shetuanLogin.setPerfect(1);
                shetuanLoginRepository.save(shetuanLogin);
            }
            return new ResponseEntity<>("Perfect success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sno not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getInfo")
    public ResponseEntity<ShetuanUser> GetInfo(@RequestParam String sno) {
        ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);
        return new ResponseEntity<>(shetuanUser, HttpStatus.OK);
    }

    @PostMapping("/updateInfo")
    public ResponseEntity<?> updateInfo(@RequestBody PerfectRequest perfectRequest) {
        boolean isUpdate = shetuanUserService.UpdateInfo(perfectRequest.getSno(), perfectRequest.getNickname(), perfectRequest.getBelong(), perfectRequest.getQq(), perfectRequest.getPhone());
        if (isUpdate) {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sno not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("deleteUser")
    public ResponseEntity<String> DeleteUser(@RequestParam String sno) {
        boolean isDelete = shetuanUserService.DeleteUser(sno);
        if(isDelete) {
            return new ResponseEntity<>("Delete success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }
    }

    public static class PerfectRequest {
        private String sno;
        private String nickname;
        private String name;
        private String belong;
        private String qq;
        private String mail;
        private String phone;

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
