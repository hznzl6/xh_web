package cn.ganxy03.xh_website.service;

public interface TencentSmsService {

    boolean sendSms(String phoneNumber);

    boolean verifyCode(String phoneNumber, String code);

}
