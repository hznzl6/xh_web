package com.example.mass.controller.backstage;

import com.example.mass.entity.backstage.QuestionBank;
import com.example.mass.server.backstage.QuestionBankServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("backstage-bank")
public class QuestionBankController {
    private final QuestionBankServer questionBankServer;

    public QuestionBankController(QuestionBankServer questionBankServer) {
        this.questionBankServer = questionBankServer;
    }

    @PostMapping("add")
    public ResponseEntity<String> addOne(@RequestBody Question question) {
        if(questionBankServer.AddOne(question.getStem(), question.getA(), question.getB(), question.getC(), question.getD(), question.getAnswer(), question.getDescription())) {
            return new ResponseEntity<>("Add success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Question have exited", HttpStatus.OK);
    }

    @PostMapping("del")
    public ResponseEntity<String> delOne(@RequestParam String stem) {
        questionBankServer.DelOne(stem);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<String> updateOne(@RequestBody Question question) {
        questionBankServer.UpdateOne(question.getStem(), question.getA(), question.getB(), question.getC(), question.getD(), question.getAnswer(), question.getDescription());
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

    @PostMapping("getAll")
    public ResponseEntity<List> getAll() {
        return new ResponseEntity<>(questionBankServer.getAll(), HttpStatus.OK);
    }

    @PostMapping("getRandom")
    public ResponseEntity<List> getRandom() {
        return new ResponseEntity<>(questionBankServer.getRandom(), HttpStatus.OK);
    }

    public static class Question {
        private String stem;
        private String a;
        private String b;
        private String c;
        private String d;
        private String answer;
        private String description;

        public String getStem() {
            return stem;
        }

        public void setStem(String stem) {
            this.stem = stem;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
