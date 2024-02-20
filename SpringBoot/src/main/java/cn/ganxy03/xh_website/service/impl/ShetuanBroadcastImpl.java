package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanBroadcast;
import cn.ganxy03.xh_website.repository.ShetuanBroadcastRepository;
import cn.ganxy03.xh_website.service.ShetuanBroadcastService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShetuanBroadcastImpl implements ShetuanBroadcastService {
    private final ShetuanBroadcastRepository shetuanBroadcastRepository;

    public ShetuanBroadcastImpl(ShetuanBroadcastRepository shetuanBroadcastRepository) {
        this.shetuanBroadcastRepository = shetuanBroadcastRepository;
    }

    @Override
    public boolean PostBroadcast(String mass, String title, String content) {
        ShetuanBroadcast shetuanBroadcast = shetuanBroadcastRepository.findByMassAndTitleAndStatus(mass, title, 0);
        if(shetuanBroadcast != null) {
            return false;
        } else {
            ShetuanBroadcast shetuanBroadcast1 = new ShetuanBroadcast();
            shetuanBroadcast1.setMass(mass);
            shetuanBroadcast1.setTitle(title);
            shetuanBroadcast1.setContent(content);
            shetuanBroadcast1.setCreate_time(LocalDateTime.now());
            shetuanBroadcastRepository.save(shetuanBroadcast1);
            return true;
        }
    }

    @Override
    public boolean UpdateBroadcast(String mass, String title, String content) {
        ShetuanBroadcast shetuanBroadcast = shetuanBroadcastRepository.findByMassAndTitleAndStatus(mass, title, 0);
        if (shetuanBroadcast != null) {
            shetuanBroadcast.setContent(content);
            shetuanBroadcast.setUpdate_time(LocalDateTime.now());
            shetuanBroadcastRepository.save(shetuanBroadcast);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ShetuanBroadcast> GetAllBroadcast(String mass) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<ShetuanBroadcast> shetuanBroadcasts = shetuanBroadcastRepository.findAllByMassAndStatus(mass, 0, sort);
        return shetuanBroadcasts;
    }

    @Override
    public boolean DeleteBroadcast(String mass, String title) {
        ShetuanBroadcast shetuanBroadcast = shetuanBroadcastRepository.findByMassAndTitleAndStatus(mass, title, 0);
        if (shetuanBroadcast != null) {
            shetuanBroadcast.setStatus(1);
            shetuanBroadcastRepository.save(shetuanBroadcast);
            return true;
        } else {
            return false;
        }
    }
}
