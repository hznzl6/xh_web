package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanIntro;
import cn.ganxy03.xh_website.service.ShetuanIntroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shetuan-Intro")
public class ShetuanIntroController {
    private final ShetuanIntroService shetuanIntroService;

    public ShetuanIntroController(ShetuanIntroService shetuanIntroService) {
        this.shetuanIntroService = shetuanIntroService;
    }

    @PostMapping("intro-update")
    public ResponseEntity<String> UpdateIntro(@RequestBody UpdateIntro updateIntro) {
        boolean isUpdate = shetuanIntroService.UpdateIntro(updateIntro.getMass(), updateIntro.getTitle(), updateIntro.getHtml());
        if (isUpdate) {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("intro-getIntro")
    public ResponseEntity<List> GetIntroByMass(@RequestParam String mass) {
        List<ShetuanIntro> shetuanIntros = shetuanIntroService.getIntro(mass);
        return new ResponseEntity<>(shetuanIntros, HttpStatus.OK);
    }

//    @PostMapping("tempSetZero")
//    public ResponseEntity<String> TempZero() {
//        shetuanIntroService.SetZero();
//        return new ResponseEntity<>("ok", HttpStatus.OK);
//    }

    public static class UpdateIntro {
        private String mass;
        private String title;
        private String html;

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }
    }
}
