package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanCredit;
import cn.ganxy03.xh_website.service.ShetuanCreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shetuan-credit")
public class ShetuanCreditController {
    public final ShetuanCreditService shetuanCreditService;
    public ShetuanCreditController(ShetuanCreditService shetuanCreditService) {
        this.shetuanCreditService = shetuanCreditService;
    }

    @PostMapping("credit-create")
    public ResponseEntity<String> CreateCredit(@RequestBody CreditData creditData) {
        boolean isCreate = shetuanCreditService.Create(creditData.getSemester(), creditData.getFounder(), creditData.getDeclaration(), creditData.getDeadline());
        if(isCreate) {
            return new ResponseEntity<>("Create success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        }
    }

    @PostMapping("credit-getAll")
    public ResponseEntity<List> GetAllCredit() {
        List<ShetuanCredit> shetuanCredits = shetuanCreditService.GetAll();
        return new ResponseEntity<>(shetuanCredits, HttpStatus.OK);
    }

    @PostMapping("credit-judgtStatus")
    public ResponseEntity<?> JudgeSetStatus() {
        shetuanCreditService.JudgeStatus();
        return new ResponseEntity<>("Judge success", HttpStatus.OK);
    }

    public static class CreditData {
        private String semester;
        private String founder;
        private String declaration;
        private String deadline;

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getFounder() {
            return founder;
        }

        public void setFounder(String founder) {
            this.founder = founder;
        }

        public String getDeclaration() {
            return declaration;
        }

        public void setDeclaration(String declaration) {
            this.declaration = declaration;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }
    }
}
