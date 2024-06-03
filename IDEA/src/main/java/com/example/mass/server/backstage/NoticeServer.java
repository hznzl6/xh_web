package com.example.mass.server.backstage;

import com.example.mass.entity.backstage.Notice;

import java.util.List;

public interface NoticeServer {
    boolean PostNotice(String title, String content, String poster);

    void DeleteNotice(String title);

    List<Notice> getNotices();

}
