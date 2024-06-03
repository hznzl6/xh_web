package com.example.mass.server.backstage.impl;

import com.example.mass.entity.backstage.QuestionBank;
import com.example.mass.repository.backstage.QuestionBankRepository;
import com.example.mass.server.backstage.QuestionBankServer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionBankImpl implements QuestionBankServer {
    private final QuestionBankRepository questionBankRepository;

    public QuestionBankImpl(QuestionBankRepository questionBankRepository) {
        this.questionBankRepository = questionBankRepository;
    }

    public boolean AddOne(String stem, String a, String b, String c, String d, String answer, String description) {
        QuestionBank questionBank = questionBankRepository.findByStem(stem);

        if(questionBank == null) {
            QuestionBank target = new QuestionBank();
            target.setStem(stem);
            target.setA(a);
            target.setB(b);
            target.setC(c);
            target.setD(d);
            target.setAnswer(answer);
            target.setDescription(description);
            questionBankRepository.save(target);
            return true;
        }
        return false;
    }

    public void DelOne(String stem) {
        QuestionBank questionBank = questionBankRepository.findByStem(stem);
        questionBankRepository.delete(questionBank);
    }

    public void UpdateOne(String stem, String a, String b, String c, String d, String answer, String description) {
        QuestionBank target = questionBankRepository.findByStem(stem);
        target.setStem(stem);
        target.setA(a);
        target.setB(b);
        target.setC(c);
        target.setD(d);
        target.setAnswer(answer);
        target.setDescription(description);
        questionBankRepository.save(target);
    }

    public List<QuestionBank> getAll() {
        return questionBankRepository.findAll();
    }

    public List<QuestionBank> getRandom() {
        List<QuestionBank> allQuestions = getAll();
        List<QuestionBank> randomQuestions = new ArrayList<>();
        // 如果题库中的问题数量小于等于30，则直接返回全部问题
        if (allQuestions.size() <= 30) {
            return allQuestions;
        }
        // 使用随机数生成器获取30个随机索引
        Random random = new Random();
        List<Integer> randomIndexes = new ArrayList<>();
        while (randomIndexes.size() < 30) {
            int randomIndex = random.nextInt(allQuestions.size());
            if (!randomIndexes.contains(randomIndex)) {
                randomIndexes.add(randomIndex);
            }
        }
        // 根据随机索引获取对应的问题，并添加到随机问题列表中
        for (int index : randomIndexes) {
            randomQuestions.add(allQuestions.get(index));
        }
        return randomQuestions;
    }
}
