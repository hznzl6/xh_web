package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.config.RedisUtil;
import cn.ganxy03.xh_website.service.TencentSmsService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Random;

@Service
public class TencentSmsImpl implements TencentSmsService {
    private final RedisUtil redisUtil;

    public TencentSmsImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
    public String generateTemplateParam() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // 生成一个六位数的随机数
        return String.valueOf(randomNumber);
    }

    @Override
    public boolean sendSms(String phoneNumber) {
        if(!isValidPhoneNumber(phoneNumber)) {
            System.out.println("手机号码格式不正确");
            return false;
        }
        try {
            phoneNumber = phoneNumber.replace("+", "");
            phoneNumber = "+86" + phoneNumber;
            // 实例化一个http选项
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象
            Credential credential = new Credential("AKIDGXn296xh0GMMKH3gD3IQac0INNmDi7X0", "hQ5eBpK1iJxUs43s12ytfFdyHl4DD8xb");
            SmsClient client = new SmsClient(credential, "ap-beijing");
            // 实例化一个请求对象
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet = { phoneNumber };
            req.setPhoneNumberSet(phoneNumberSet);
            req.setSmsSdkAppid("1400847193");
            req.setSign("ganxy03Cn网");
            req.setTemplateID("1937197");
            // 生成模板参数（验证码）
            String templateParam = generateTemplateParam();
            String[] templateParamSet = { templateParam };
            req.setTemplateParamSet(templateParamSet);
            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
            // 打印手机号码和验证码
//            System.out.println("手机号码：" + phoneNumber);
//            System.out.println("验证码：" + templateParam);
            redisUtil.selectDatabase(3);
            redisUtil.set(phoneNumber, templateParam, 3000);
            return true;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return false;
        }
    }


    @Override
    public boolean verifyCode(String phoneNumber, String code) {
        redisUtil.selectDatabase(3);
        String storedCode = redisUtil.get("+86"+phoneNumber);
        if(storedCode.equals(code)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        // 在这里添加手机号码格式校验的逻辑，可以使用正则表达式等方法
        // 下面是一个简单的示例，只验证手机号码是否是11位数字
//        return phoneNumber.matches("\\d{11}");
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(16[6-7])|(17[0-8])|(18[0-9])|(19[8|9]))\\d{8}$";
        return phoneNumber.matches(regex);
    }

}
