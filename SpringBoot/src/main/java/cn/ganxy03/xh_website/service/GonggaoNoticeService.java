package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.GonggaoNotice;

import java.util.List;
import java.util.Map;

public interface GonggaoNoticeService {
    boolean PostNotice(String title, String content, String publisher);

    List<Map<String, Object>> GetTitle();

    GonggaoNotice GetContentByTitle(String title);

    void Hits(String title);
}
