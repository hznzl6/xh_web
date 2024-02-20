package cn.ganxy03.xh_website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("ganxy03.cn"); // 邮件主机
        mailSender.setPort(25); // 邮件端口
        mailSender.setUsername(""); // 邮件用户名
        mailSender.setPassword(""); // 邮件密码
        return mailSender;
    }
}