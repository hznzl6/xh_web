package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanMass;
import cn.ganxy03.xh_website.antity.ShetuanUser;
import cn.ganxy03.xh_website.repository.ShetuanMassRepository;
import cn.ganxy03.xh_website.repository.ShetuanUserRepository;
import cn.ganxy03.xh_website.service.ShetuanMassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShetuanMassImpl implements ShetuanMassService {
    private final ShetuanMassRepository shetuanMassRepository;
    private final ShetuanUserRepository shetuanUserRepository;

    @Autowired
    public ShetuanMassImpl(ShetuanMassRepository shetuanMassRepository, ShetuanUserRepository shetuanUserRepository) {
        this.shetuanMassRepository = shetuanMassRepository;
        this.shetuanUserRepository = shetuanUserRepository;
    }

    public boolean Update(String name, String info, String qq, String president, String create_time) {
        ShetuanMass shetuanMass = shetuanMassRepository.findByName(name);
        if(shetuanMass != null) {
            shetuanMass.setName(name);
            shetuanMass.setInfo(info);
            shetuanMass.setQq(qq);
            shetuanMass.setPresident(president);
            shetuanMass.setCreate_time(create_time);
            shetuanMassRepository.save(shetuanMass);
        } else {
            ShetuanMass shetuanMass2 = new ShetuanMass();
            shetuanMass2.setName(name);
            shetuanMass2.setInfo(info);
            shetuanMass2.setQq(qq);
            shetuanMass2.setPresident(president);
            shetuanMass2.setCreate_time(create_time);
            shetuanMassRepository.save(shetuanMass2);
        }
        return true;
    }

    @Override
    public List<ShetuanUser> GetMember(String mass) {
        ShetuanMass shetuanMass = shetuanMassRepository.findByName(mass);
        String member = shetuanMass.getMember();
        String[] idArray = member.split(",");
        List<ShetuanUser> shetuanUsers = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        for (String index : idArray) {
            idList.add(Integer.parseInt(index));
        }
        for (Integer id : idList) {
            ShetuanUser shetuanUser = shetuanUserRepository.getAllById(id);
            if (shetuanUser != null && shetuanUser.getStatus() == 0) {
                shetuanUsers.add(shetuanUser);
            }
        }
        return shetuanUsers;
    }
}
