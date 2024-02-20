package cn.ganxy03.xh_website.service;


import java.util.List;

public interface AiMessageService {
    List<Object> GetAllMessage();

    List<Object> GetByUUID(String uuid);

}
