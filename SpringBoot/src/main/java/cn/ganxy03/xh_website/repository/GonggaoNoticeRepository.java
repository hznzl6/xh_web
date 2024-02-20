package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.GonggaoNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GonggaoNoticeRepository extends JpaRepository<GonggaoNotice, Integer> {

    GonggaoNotice getByTitle(String title);
}
