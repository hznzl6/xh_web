package cn.ganxy03.xh_website.repository;

import cn.ganxy03.xh_website.antity.ShetuanBroadcast;
import io.lettuce.core.GeoArgs;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShetuanBroadcastRepository extends JpaRepository<ShetuanBroadcast, Integer> {

    ShetuanBroadcast findByMassAndTitleAndStatus(String mass, String title, Integer status);

    List<ShetuanBroadcast> findAllByMassAndStatus(String mass, Integer status, Sort sort);
}
