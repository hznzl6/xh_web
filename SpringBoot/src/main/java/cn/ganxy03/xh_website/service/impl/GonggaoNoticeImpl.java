package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.GonggaoNotice;
import cn.ganxy03.xh_website.repository.GonggaoNoticeRepository;
import cn.ganxy03.xh_website.service.GonggaoNoticeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GonggaoNoticeImpl implements GonggaoNoticeService {
    private final GonggaoNoticeRepository gonggaoNoticeRepository;

    public GonggaoNoticeImpl(GonggaoNoticeRepository gonggaoNoticeRepository) {
        this.gonggaoNoticeRepository = gonggaoNoticeRepository;
    }

    @Override
    public boolean PostNotice(String title, String content, String publisher) {
        GonggaoNotice gonggaoNotice = gonggaoNoticeRepository.getByTitle(title);
        if(gonggaoNotice == null) {
            GonggaoNotice gonggaoNotice1 = new GonggaoNotice();
            gonggaoNotice1.setContent(content);
            gonggaoNotice1.setTitle(title);
            gonggaoNotice1.setPublisher(publisher);
            gonggaoNotice1.setHits(0);
            gonggaoNotice1.setCreate_time(LocalDateTime.now());
            gonggaoNoticeRepository.save(gonggaoNotice1);
            return true;
        } else {
            gonggaoNotice.setContent(content);
            gonggaoNotice.setPublisher(publisher);
            gonggaoNotice.setHits(0);
            gonggaoNotice.setUpdate_time(LocalDateTime.now());
            gonggaoNoticeRepository.save(gonggaoNotice);
            return false;
        }
    }

//    @Override
//    public List<Map<String, Object>> GetTitle() {
//        List<GonggaoNotice> gonggaoNotices = gonggaoNoticeRepository.findAll();
//        List<Map<String, Object>> titleAndTimeList = new ArrayList<>();
//        for (GonggaoNotice notice : gonggaoNotices) {
//            Map<String, Object> titleAndTime = new HashMap<>();
//            titleAndTime.put("title", notice.getTitle());
//            titleAndTime.put("create_time", notice.getCreate_time());
//            titleAndTimeList.add(titleAndTime);
//        }
//        return titleAndTimeList;
//    }

    @Override
    public List<Map<String, Object>> GetTitle() {
        List<GonggaoNotice> gonggaoNotices = gonggaoNoticeRepository.findAll();
        List<Map<String, Object>> titleAndTimeList = new ArrayList<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (GonggaoNotice notice : gonggaoNotices) {
            Map<String, Object> titleAndTime = new HashMap<>();
            titleAndTime.put("title", notice.getTitle());
            titleAndTime.put("create_time", notice.getCreate_time().format(formatter));
            titleAndTimeList.add(titleAndTime);
        }
        return titleAndTimeList;
    }

    @Override
    public GonggaoNotice GetContentByTitle(String title) {
        GonggaoNotice gonggaoNotice = gonggaoNoticeRepository.getByTitle(title);
        return gonggaoNotice;
    }

    @Override
    public void Hits(String title) {
        GonggaoNotice gonggaoNotice = gonggaoNoticeRepository.getByTitle(title);
        gonggaoNotice.setHits(gonggaoNotice.getHits() + 1);
        gonggaoNoticeRepository.save(gonggaoNotice);
    }
}
