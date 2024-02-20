package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanIntro;
import cn.ganxy03.xh_website.repository.ShetuanIntroRepository;
import cn.ganxy03.xh_website.service.ShetuanIntroService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShetuanIntroImpl implements ShetuanIntroService {
    private final ShetuanIntroRepository shetuanIntroRepository;
    public ShetuanIntroImpl(ShetuanIntroRepository shetuanIntroRepository) {
        this.shetuanIntroRepository = shetuanIntroRepository;
    }

    @Override
    public boolean UpdateIntro(String mass, String title, String html) {
        ShetuanIntro shetuanIntro = shetuanIntroRepository.getByMass(mass);
        shetuanIntro.setTitle(title);
        shetuanIntro.setHtml(html);
        shetuanIntro.setUpdate_time(LocalDateTime.now());
        shetuanIntroRepository.save(shetuanIntro);
        return true;
    }
    @Override
    public List<ShetuanIntro> getIntro(String mass) {
        List<ShetuanIntro> shetuanIntros = shetuanIntroRepository.getListByMass(mass);
        return shetuanIntros;
    }

//    @Override
//    public void SetZero() {
//        List<ShetuanIntro> shetuanIntros = shetuanIntroRepository.findAll();
////        for(ShetuanIntro intro : shetuanIntros) {
////            intro.setMemnums(0);
////            intro.setAcnums(0);
////            intro.setArtnums(0);
////            intro.setHitnums(0);
////            intro.setComnums(0);
////            intro.setBronums(0);
////            intro.setNums0(0);
////            intro.setCrenums(0);
////            // 保存更新后的ShetuanIntro对象
////            shetuanIntroRepository.save(intro);
////        }
//        System.out.println(shetuanIntros);
//    }
}
