package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanManager;
import cn.ganxy03.xh_website.repository.ShetuanManagerRepository;
import cn.ganxy03.xh_website.service.ShetuanManagerService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShetuanManagerImpl implements ShetuanManagerService {
    private final ShetuanManagerRepository shetuanManagerRepository;

    public ShetuanManagerImpl(ShetuanManagerRepository shetuanManagerRepository) {
        this.shetuanManagerRepository = shetuanManagerRepository;
    }
    @Override
    public boolean GenerateAccount(String mass) {
        Random random = new Random();
        int number = random.nextInt(10000);
        String account = "hnldst" + String.format("%04d", number);
        ShetuanManager shetuanManager = shetuanManagerRepository.getByMass(mass);
        if(shetuanManager != null) {
            shetuanManager.setAccount(account);
            shetuanManagerRepository.save(shetuanManager);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Updatepwd(String account, String pwd) {
        ShetuanManager shetuanManager = shetuanManagerRepository.getByAccount(account);
        if(shetuanManager != null) {
            shetuanManager.setPwd(pwd);
            shetuanManagerRepository.save(shetuanManager);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean UpdateInfo(String account, String mail, String qq, String phone) {
        ShetuanManager shetuanManager = shetuanManagerRepository.getByAccount(account);
        if(shetuanManager != null) {
            shetuanManager.setMail(mail);
            shetuanManager.setQq(qq);
            shetuanManager.setPhone(phone);
            shetuanManagerRepository.save(shetuanManager);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean Login(String account, String pwd) {
        ShetuanManager shetuanManager = shetuanManagerRepository.getByAccount(account);
        if(shetuanManager != null && shetuanManager.getPwd().equals(pwd)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ShetuanManager GetInfo(String account) {
        return shetuanManagerRepository.getByAccount(account);
    }

    @Override
    public String ReturnMass(String account) {
        ShetuanManager shetuanManager = shetuanManagerRepository.getByAccount(account);
        return shetuanManager.getMass();
    }
}
