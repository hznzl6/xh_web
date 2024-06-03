package com.example.mass.controller.manager;

import com.example.mass.server.manager.MApplyActivityServer;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager-applyActivity")
@Api(value = "Article Controller", description = "社团管理系统的活动申请接口")
public class MApplyActivityController {
    private final MApplyActivityServer mApplyActivityServer;

    public MApplyActivityController(MApplyActivityServer mApplyActivityServer) {
        this.mApplyActivityServer = mApplyActivityServer;
    }

    @PostMapping("/apply")
    public ResponseEntity<String> Apply(@RequestBody ApplyActivity applyActivity) {
        if(mApplyActivityServer.applyNew(applyActivity.getMass(), applyActivity.getTitle(), applyActivity.getDescribe(), applyActivity.getSno(), applyActivity.getRemark())) {
            return new ResponseEntity<>("Apply success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Activity have exited", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> Update(@RequestBody ApplyActivity applyActivity) {
        mApplyActivityServer.updateData(applyActivity.getMass(), applyActivity.getTitle(), applyActivity.getDescribe(), applyActivity.getSno(), applyActivity.getRemark());
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<String> Delete(@RequestParam String mass, @RequestParam String title) {
        mApplyActivityServer.deleteData(mass, title);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

//    获取社团的全部活动
    @PostMapping("getActivities")
    public ResponseEntity<List> getActivities(@RequestParam String mass) {
        if(mApplyActivityServer.getActivities(mass) != null) {
            return new ResponseEntity<>(mApplyActivityServer.getActivities(mass), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("getAll")
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(mApplyActivityServer.getAll(), HttpStatus.OK);
    }

    public static class ApplyActivity {
        private String mass;
        private String title;
        private String describe;
        private String sno;
        private String remark;

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

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
