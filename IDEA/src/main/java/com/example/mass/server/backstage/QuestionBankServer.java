package com.example.mass.server.backstage;

import com.example.mass.entity.backstage.QuestionBank;

import java.util.List;

public interface QuestionBankServer {

    boolean AddOne(String stem, String a, String b, String c, String d, String answer, String description);

    void DelOne(String stem);

    void UpdateOne(String stem, String a, String b, String c, String d, String answer, String description);

    List<QuestionBank> getAll();

    List<QuestionBank> getRandom();

}
