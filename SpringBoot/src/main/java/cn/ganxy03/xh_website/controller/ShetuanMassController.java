package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanMass;
import cn.ganxy03.xh_website.antity.ShetuanUser;
import cn.ganxy03.xh_website.repository.ShetuanMassRepository;
import cn.ganxy03.xh_website.service.ShetuanMassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shetuan-mass")
public class ShetuanMassController {
    private final ShetuanMassService shetuanMassService;
    private final ShetuanMassRepository shetuanMassRepository;

    @Autowired
    public ShetuanMassController(ShetuanMassService shetuanMassService, ShetuanMassRepository shetuanMassRepository) {
        this.shetuanMassService = shetuanMassService;
        this.shetuanMassRepository = shetuanMassRepository;
    }

    @PostMapping("/mass-update")
    public ResponseEntity<String> UpdateMass(@RequestBody UpdateRequest updateRequest) {
        boolean isUpdate = shetuanMassService.Update(updateRequest.getName(), updateRequest.getInfo(), updateRequest.getQq(), updateRequest.getPresident(), updateRequest.getCreate_time());
        if(isUpdate) {
            return new ResponseEntity<>("update success!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sno not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/mass-getMember")
    public ResponseEntity<List<ShetuanUser>> GetMember(@RequestParam String mass) {
        List<ShetuanUser> shetuanUser = shetuanMassService.GetMember(mass);
        return new ResponseEntity<>(shetuanUser, HttpStatus.OK);
    }

    public static class UpdateRequest {
        private String name;
        private String info;
        private String qq;
        private String president;
        private String create_time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getPresident() {
            return president;
        }

        public void setPresident(String president) {
            this.president = president;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
