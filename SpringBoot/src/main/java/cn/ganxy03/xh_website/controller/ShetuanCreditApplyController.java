package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanCreditApply;
import cn.ganxy03.xh_website.antity.ShetuanUser;
import cn.ganxy03.xh_website.service.ShetuanCreditApplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shetuan-creditApply")
public class ShetuanCreditApplyController {
    private final ShetuanCreditApplyService shetuanCreditApplyService;

    public ShetuanCreditApplyController(final ShetuanCreditApplyService shetuanCreditApplyService) {
        this.shetuanCreditApplyService = shetuanCreditApplyService;
    }

    @PostMapping("apply-credit")
    public ResponseEntity<String> ApplyCredit(@RequestBody ApplyCredit applyCredit) {
//        String[] labelsArray = applyCredit.getLabels().toArray(new String[0]);
        System.out.println(applyCredit.getSnos());
        boolean isCreate = shetuanCreditApplyService.ApplyCredit(applyCredit.getSnos(), applyCredit.getSemester(), applyCredit.getMass());
        if (isCreate) {
            return new ResponseEntity<>("Create success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        }
    }

    @PostMapping("add-credit")
    public ResponseEntity<String> AddCredit(@RequestBody ApplyCredit applyCredit) {
        shetuanCreditApplyService.AddCredit(applyCredit.getSnos(), applyCredit.getSemester(), applyCredit.getMass());
        return new ResponseEntity<>("Add success", HttpStatus.OK);
    }
    @PostMapping("remove-credit")
    public ResponseEntity<String> RemoveCredit(@RequestBody RemoveCredit removeCredit) {
        shetuanCreditApplyService.RemoveCredit(removeCredit.getSno(), removeCredit.getSemester(), removeCredit.getMass());
        return new ResponseEntity<>("Remove success", HttpStatus.OK);
    }

    @PostMapping("getAllByMass-credit")
    public ResponseEntity<List> GetAllByMass(@RequestParam String mass) {
        List<ShetuanCreditApply> shetuanCreditApplies = shetuanCreditApplyService.GetItemByMass(mass);
        return new ResponseEntity<>(shetuanCreditApplies, HttpStatus.OK);
    }

    @PostMapping("returnID")
    public ResponseEntity<Integer> ReturnID(@RequestParam String sno) {
        Integer id = shetuanCreditApplyService.SearchId(sno);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

//    @PostMapping("returnBelong")
    @PostMapping(value = "returnBelong", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> ReturnBelong(@RequestParam String sno) {
        String belong = shetuanCreditApplyService.SearchBelong(sno);
        return new ResponseEntity<>(belong, HttpStatus.OK);
    }

    @PostMapping("getAllBySemester")
    public ResponseEntity<List> GetAllBySemester() {
        List<ShetuanCreditApply> shetuanCreditApplies = shetuanCreditApplyService.GetAllCredit();
        return new ResponseEntity<>(shetuanCreditApplies, HttpStatus.OK);
    }

    @PostMapping("passCredit")
    public ResponseEntity<String> PassCredit(@RequestParam String semester, @RequestParam String mass) {
        shetuanCreditApplyService.PassCredit(semester, mass);
        return new ResponseEntity<>("Pass success", HttpStatus.OK);
    }

    @PostMapping("ReBackCredit")
    public ResponseEntity<String> ReBackCredit(@RequestParam String semester, @RequestParam String mass) {
        shetuanCreditApplyService.ReBackCredit(semester, mass);
        return new ResponseEntity<>("ReBack success", HttpStatus.OK);
    }

    @PostMapping("getCreditPeople")
    public ResponseEntity<List> GetCreditPeople(@RequestParam String[] ids) {
        List<ShetuanUser> shetuanUsers = shetuanCreditApplyService.GetInfoById(ids);
        return new ResponseEntity<>(shetuanUsers, HttpStatus.OK);
    }

    public static class RemoveCredit {
        private String sno;
        private String semester;
        private String mass;

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }
    }

    public static class ApplyCredit {
        private String[] snos;
        private String semester;
        private String mass;

        public String[] getSnos() {
            return snos;
        }

        public void setSnos(String[] snos) {
            this.snos = snos;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }
    }
}
