package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.antity.ShetuanBroadcast;

import java.util.List;

public interface ShetuanBroadcastService {

    boolean PostBroadcast(String mass, String title, String content);

    boolean UpdateBroadcast(String mass, String title, String content);

    List<ShetuanBroadcast> GetAllBroadcast(String mass);

    boolean DeleteBroadcast(String mass, String title);
}
