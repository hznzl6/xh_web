package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanCredit;
import cn.ganxy03.xh_website.repository.ShetuanCreditRepository;
import cn.ganxy03.xh_website.service.ShetuanCreditService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ShetuanCreditImpl implements ShetuanCreditService {
    public final ShetuanCreditRepository shetuanCreditRepository;
    public ShetuanCreditImpl(ShetuanCreditRepository shetuanCreditRepository) {
        this.shetuanCreditRepository = shetuanCreditRepository;
    }

    @Override
    public boolean Create(String semester, String founder, String declaration, String deadline) {
        ShetuanCredit shetuanCredit = shetuanCreditRepository.findBySemester(semester);
        ShetuanCredit targetCredit;
        boolean isCreate = false;
        if(shetuanCredit != null) {
            targetCredit = shetuanCredit;
        } else {
            targetCredit = new ShetuanCredit();
            isCreate = true;
        }
        targetCredit.setSemester(semester);
        targetCredit.setFounder(founder);
        targetCredit.setDeclaration(declaration);
        targetCredit.setDeadline(deadline);
        targetCredit.setStatus(0);
        shetuanCreditRepository.save(targetCredit);
        return isCreate;
    }

    @Override
    public List<ShetuanCredit> GetAll() {
        List<ShetuanCredit> shetuanCredits = shetuanCreditRepository.findAll();
        return shetuanCredits;
    }

    @Override
    public void JudgeStatus() {
        List<ShetuanCredit> shetuanCredits = shetuanCreditRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        for (ShetuanCredit credit : shetuanCredits) {
            String declarationStr = credit.getDeclaration();
            String deadlineStr = credit.getDeadline();
            LocalDateTime declarationTime = LocalDateTime.parse(declarationStr, formatter);
            LocalDateTime deadlineTime = LocalDateTime.parse(deadlineStr, formatter);
            int status;
            if (currentTime.isBefore(declarationTime)) {
                status = 0;
            } else if (currentTime.isBefore(deadlineTime)) {
                status = 1;
            } else {
                status = 2;
            }
            credit.setStatus(status);
            shetuanCreditRepository.save(credit);
        }

    }
}
