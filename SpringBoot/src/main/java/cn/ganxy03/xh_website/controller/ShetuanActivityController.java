package cn.ganxy03.xh_website.controller;

import cn.ganxy03.xh_website.antity.ShetuanActivity;
import cn.ganxy03.xh_website.antity.ShetuanUser;
import cn.ganxy03.xh_website.service.ShetuanActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shetuan-activity")
public class ShetuanActivityController {
    private final ShetuanActivityService shetuanActivityService;

    public ShetuanActivityController(ShetuanActivityService shetuanActivityService) {
        this.shetuanActivityService = shetuanActivityService;
    }

    @PostMapping("applyEvent")
    public ResponseEntity<String> CreateEvent(@RequestBody CreateEvent createEvent) {
        boolean isCreate = shetuanActivityService.ApplyEvent(createEvent.getMass(), createEvent.getEvent(), createEvent.getDescribe(), createEvent.getPerson(), createEvent.getPhone(), createEvent.getExpenditure(), createEvent.getStart_time(), createEvent.getEnd_time(), createEvent.getMark());
        if(isCreate) {
            return new ResponseEntity<>("Apply success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update success", HttpStatus.OK);
        }
    }

    @PostMapping("agreeEvent")
    public ResponseEntity<String> AgreeEvent(@RequestParam String mass, @RequestParam String event) {
        boolean isAgree = shetuanActivityService.AgreeEvent(mass, event);
        if (isAgree) {
            return new ResponseEntity<>("Agree success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Agree failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("rejectEvent")
    public ResponseEntity<String> RejectEvent(@RequestParam String mass, @RequestParam String event) {
        boolean isReject = shetuanActivityService.RejectEvent(mass, event);
        if (isReject) {
            return new ResponseEntity<>("Reject success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reject failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("overEvent")
    public ResponseEntity<String> OverEvent(@RequestParam String mass, @RequestParam String event) {
        boolean isOver = shetuanActivityService.OverEvent(mass, event);
        if(isOver) {
            return new ResponseEntity<>("Over success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Over failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("getActiveEvent")
    public ResponseEntity<?> GetActiveEvent(@RequestParam String mass) {
        List<ShetuanActivity> shetuanActivity  = shetuanActivityService.GetActiveEvent(mass);
        return new ResponseEntity<>(shetuanActivity, HttpStatus.OK);
    }

    @PostMapping("getAllEventByMass")
    public ResponseEntity<List> GetAllEventByMass(@RequestParam String mass) {
        List<ShetuanActivity> shetuanActivities = shetuanActivityService.findAllByMass(mass);
        return new ResponseEntity<>(shetuanActivities, HttpStatus.OK);
    }

    @PostMapping("getAllEvent")
    public ResponseEntity<List> GetAllEvent() {
        List<ShetuanActivity> shetuanActivities = shetuanActivityService.getAllActivity();
        return new ResponseEntity<>(shetuanActivities, HttpStatus.OK);
    }

    @PostMapping("getAllEventByMassAndStatus")
    public ResponseEntity<List> GetAllEventByMassAndStatus(@RequestParam String mass) {
        List<ShetuanActivity> shetuanActivities = shetuanActivityService.getAllByMassAndStatus(mass);
        return new ResponseEntity<>(shetuanActivities, HttpStatus.OK);
    }

    @PostMapping("signEvent")
    public ResponseEntity<String> SignEvent(@RequestParam String mass, @RequestParam String event, @RequestParam String sno) {
        boolean isSign = shetuanActivityService.SignEvent(sno, mass, event);
        if(isSign) {
            return new ResponseEntity<>("Sign success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Have signed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("actualEvent")
    public ResponseEntity<String> ActualEvent(@RequestParam String mass, @RequestParam String event, @RequestParam String sno) {
        boolean isActual = shetuanActivityService.ActualEvent(mass, event, sno);
        if(isActual) {
            return new ResponseEntity<>("Actual success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Have actualed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("YiactualEvent")
    public ResponseEntity<String> YiActualEvent(@RequestParam String mass, @RequestParam String event, @RequestParam String snos) {
        Integer isActual = shetuanActivityService.YiActualEvent(mass, event, snos);
        if(isActual == 1) {
            return new ResponseEntity<>("Have actualed", HttpStatus.BAD_REQUEST);
        } else if(isActual == 2) {
            return new ResponseEntity<>("Actual success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public static class CreateEvent {
        private String mass;
        private String event;
        private String describe;
        private String person;
        private String phone;
        private String expenditure;
        private String start_time;
        private String end_time;
        private String mark;

        public String getMass() {
            return mass;
        }

        public void setMass(String mass) {
            this.mass = mass;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getPerson() {
            return person;
        }

        public void setPerson(String person) {
            this.person = person;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getExpenditure() {
            return expenditure;
        }

        public void setExpenditure(String expenditure) {
            this.expenditure = expenditure;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }
    }
}
