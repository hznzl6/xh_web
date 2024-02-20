package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanLogin;
import cn.ganxy03.xh_website.antity.ShetuanUser;
import cn.ganxy03.xh_website.repository.ShetuanLoginRepository;
import cn.ganxy03.xh_website.repository.ShetuanUserRepository;
import cn.ganxy03.xh_website.service.ShetuanUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShetuanUserImpl implements ShetuanUserService {
    private final ShetuanUserRepository shetuanUserRepository;
    private final ShetuanLoginRepository shetuanLoginRepository;
    @Autowired
    public ShetuanUserImpl(ShetuanUserRepository shetuanUserRepository, ShetuanLoginRepository shetuanLoginRepository) {
        this.shetuanUserRepository = shetuanUserRepository;
        this.shetuanLoginRepository = shetuanLoginRepository;
    }

    @Override
    public boolean Perfect(String sno, String nickname, String name, String belong, String qq, String mail, String phone) {
        ShetuanLogin shetuanLogin = shetuanLoginRepository.findBySno(sno);
        if(shetuanLogin != null) {
            shetuanLogin.setMail(mail);
            shetuanLogin.setPhone(phone);
            shetuanLoginRepository.save(shetuanLogin);
            ShetuanUser shetuanUser = new ShetuanUser();
            shetuanUser.setSno(sno);
            shetuanUser.setNickname(nickname);
            shetuanUser.setName(name);
            shetuanUser.setBelong(belong);
            shetuanUser.setQq(qq);
            shetuanUser.setMail(mail);
            shetuanUser.setPhone(phone);
            shetuanUser.setStatus(0);
            shetuanUser.setCreate_time(LocalDateTime.now());
            shetuanUserRepository.save(shetuanUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean UpdateInfo(String sno, String nickname, String belong, String qq, String phone) {
        ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);
        if (shetuanUser != null) {
            shetuanUser.setNickname(nickname);
            shetuanUser.setBelong(belong);
            shetuanUser.setQq(qq);
            shetuanUser.setPhone(phone);
            shetuanUser.setUpdate_time(LocalDateTime.now());
            shetuanUserRepository.save(shetuanUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean DeleteUser(String sno) {
        ShetuanUser shetuanUser = shetuanUserRepository.getBySno(sno);
        if(shetuanUser != null) {
            shetuanUser.setStatus(1);
            shetuanUserRepository.save(shetuanUser);
            return true;
        }
        return false;
    }
}
