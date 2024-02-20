package cn.ganxy03.xh_website.service;

import cn.ganxy03.xh_website.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Random;

@Service
public class EmailService {
    private final JavaMailSender mailSender;


    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationCode(String email) {
        // 生成六位随机数作为验证码
        int verificationCode = new Random().nextInt(900000) + 100000;
        String mailAddress = email;
        //存储redis
//        try (Jedis jedis = new Jedis("124.221.138.245", 6379)) {
//            jedis.auth("pwd");
//            jedis.select(1);
//            jedis.setex(mailAddress, 300, String.valueOf(verificationCode));
//        }
        RedisUtil.saveMailVerify(mailAddress, String.valueOf(verificationCode));
        // 创建邮件内容
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom("Ganxy@mail.ganxy03.cn");
            messageHelper.setTo(email);
            messageHelper.setSubject("邮箱验证码");
//            messageHelper.setText("<html><body><h1>Hello</h1><p>你的验证码: " + verificationCode + "，五分钟有效请尽快使用，不要告诉别人哈<br>--by Ganxy</p></body></html>", true);
            messageHelper.setText("<html><body><div style=\"background-color:#ECECEC; padding: 35px;\"><tablecellpadding=\"0\" align=\"center\" style=\"height: 100%; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\"><tbody><tr><th style=\"word-break:break-all\"><div style=\"background-color: #9400D3;border-radius: 5px 5px 0 0;height: 55px;line-height:65px;\"><font style=\"color: #fff;font-size: 24px;font-wight: 700;padding: 0 15px;margin: 10px 0\">欢迎"+mailAddress+"</font></div></th></tr><tr><td style=\"word-break:break-all\"><div style=\"padding:25px 35px 40px; background-color:#fff;opacity:0.8;\"><h2  style=\"margin: 5px 0px;\"><font color=\"#333333\" style=\"line-height: 20px;\"><font  style=\"line-height: 22px; \" size=\"4\">尊敬的用户：</font></font></h2><p>您好！感谢您使用，您的账号正在进行邮箱验证，验证码为：<font color=\"#ff8c00\">"+verificationCode+"</font>，有效期5分钟，请尽快填写验证码完成验证！</p><br><h2 style=\"margin: 5px 0px;\"><font  color=\"#333333\" style=\"line-height: 20px;\"><font style=\"line-height: 22px; \" size=\"4\">Dear user:</font></font></h2><p>Hello! Thanks for using, your account is being authenticated by email, the verification code is: <font color=\"#ff8c00\">"+verificationCode+"</font>, valid for 5 minutes. Please fill in the verification code as soon as possible!</p><div style=\"width:100%;margin:0 auto;\"><div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\"><p>&copy;2024|Ganxy</p><p>联系我：2192767718@qq.com</p><br><p>此为系统邮件，请勿回复<br>Please do not reply to this system email</p></div></div></div></td></tr></tbody></table></div></body></html>", true);
        };
        // 发送邮件
        mailSender.send(preparator);
    }
}
