package com.example.mass.server.backstage.impl;

import com.example.mass.entity.backstage.Notice;
import com.example.mass.repository.backstage.NoticeRepository;
import com.example.mass.server.backstage.NoticeServer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeImpl implements NoticeServer {
    private final NoticeRepository noticeRepository;

    public NoticeImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public boolean PostNotice(String title, String content, String poster) {
        Notice target = noticeRepository.findByTitle(title);
        if(target == null) {
            Notice notice = new Notice();
            notice.setTitle(title);
            notice.setContent(content);
            notice.setPoster(poster);
            notice.setPost_time(LocalDateTime.now());
            notice.setHits(0);
            noticeRepository.save(notice);
            return true;
        }
        return false;
    }

    public void DeleteNotice(String title) {
        Notice target = noticeRepository.findByTitle(title);
        noticeRepository.delete(target);
    }

    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }
}
