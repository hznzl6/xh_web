package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanApply;
import cn.ganxy03.xh_website.service.ShetuanApplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shetuan-apply")
public class ShetuanApplyController {
    private ShetuanApplyService shetuanApplyService;

    public ShetuanApplyController(ShetuanApplyService shetuanApplyService) {
        this.shetuanApplyService = shetuanApplyService;
    }

    @PostMapping("apply-post")
    public ResponseEntity<String> ApplyPost(@RequestBody ApplyRequest applyRequest){
        Integer isPost = shetuanApplyService.PostApplication(applyRequest.getSno(), applyRequest.getMass(), applyRequest.getDepartment(), applyRequest.getInfo());
        if(isPost == 1) {
            return new ResponseEntity<>("Post success", HttpStatus.OK);
        } else if(isPost == 2) {
            return new ResponseEntity<>("Post failed, already applied and is passed", HttpStatus.BAD_REQUEST);
        } else if(isPost == 3) {
            return new ResponseEntity<>("Post failed, already applied but not pass", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Sno not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("apply-update")
    public ResponseEntity<String> ApplyUpdate(@RequestBody ApplyRequest applyRequest) {
        Integer isUpdate = shetuanApplyService.UpdateApplication(applyRequest.getSno(), applyRequest.getMass(), applyRequest.getDepartment(), applyRequest.getInfo());
        if(isUpdate == 1) {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } else if(isUpdate == 2) {
            return new ResponseEntity<>("Not apply", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Sno not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/apply-agree")
    public ResponseEntity<String> ApplyAgree(@RequestParam String sno, @RequestParam String mass,@RequestParam String department) {
        Integer isAgree = shetuanApplyService.AgreeApplication(sno, mass, department);
        if (isAgree == 1) {
            return new ResponseEntity<>("Agree success", HttpStatus.OK);
        } else if(isAgree == 2) {
            return new ResponseEntity<>("Not apply", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Sno not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/apply-reject")
    public ResponseEntity<String> ApplyReject(@RequestParam String mass, @RequestParam String sno) {
        Integer isReject = shetuanApplyService.RejectApplication(mass, sno);
        if(isReject == 1) {
            return new ResponseEntity<>("Reject success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reject failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("apply-getWaitApply")
    public ResponseEntity<List> GetWaitApply(String mass) {
        List<ShetuanApply> shetuanApplies = shetuanApplyService.GetWaitAgree(mass);
        return new ResponseEntity<>(shetuanApplies, HttpStatus.OK);
    }

    @PostMapping("apply-getStatus")
    public ResponseEntity<String> GetStatus(@RequestParam String sno) {
        Integer status = shetuanApplyService.GetStatus(sno);
        if(status == 0) {
            return new ResponseEntity<>("Waiting...", HttpStatus.OK);
        } else if(status == 1) {
            return new ResponseEntity<>("Apply success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Apply reject", HttpStatus.OK);
        }
    }

    @PostMapping("apply-getAllPassByMass")
    public ResponseEntity<List> GetAllPassByMass(@RequestParam String mass) {
        List<ShetuanApply> shetuanApplies = shetuanApplyService.GetAllPassByMass(mass);
        return new ResponseEntity<>(shetuanApplies, HttpStatus.OK);
    }

    @PostMapping("apply-updateInfo")
    public ResponseEntity<String> UpdateInfo(@RequestParam String mass, @RequestParam String sno, @RequestParam String beforeDepartment, @RequestParam String department, @RequestParam String position) {
        boolean isUpdate = shetuanApplyService.UpdateInfo(mass, sno, beforeDepartment, department, position);
        if (isUpdate) {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("apply-deleteMember")
    public ResponseEntity<String> DeleteMember(@RequestParam String mass, @RequestParam String sno, @RequestParam String department) {
        shetuanApplyService.DeleteMember(mass, sno, department);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("apply-getAllJoinBySno")
    public ResponseEntity<List> getAllJoinBySno(@RequestParam String sno) {
        List<ShetuanApply> shetuanApplies = shetuanApplyService.GetAllJoinMass(sno);
        return new ResponseEntity<>(shetuanApplies, HttpStatus.OK);
    }

    @PostMapping("apply-getAllCheckBySno")
    public ResponseEntity<List> getAllCheckBySno(@RequestParam String sno) {
        List<ShetuanApply> shetuanApplies = shetuanApplyService.GetAllCheckBySno(sno);
        return new ResponseEntity<>(shetuanApplies, HttpStatus.OK);
    }

    public static class ApplyRequest{
        private String sno;
        private String mass;
        private String department;
        private String info;

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
