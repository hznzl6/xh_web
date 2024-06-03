package com.example.mass.server.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mass.config.RedisUtil;
import com.example.mass.entity.user.UAccount;
import com.example.mass.entity.user.Info;
import com.example.mass.entity.user.USecure;
import com.example.mass.repository.user.AccountRepository;
import com.example.mass.repository.user.UInfoRepository;
import com.example.mass.repository.user.USecureRepository;
import com.example.mass.server.user.UAccountServer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
//25、26 3
@Service
public class UAccountImpl implements UAccountServer {
    private final AccountRepository accountRepository;
    private final USecureRepository secureRepository;
    private final UInfoRepository infoRepository;
    private final RedisUtil redisUtil;

    public UAccountImpl(AccountRepository accountRepository, USecureRepository secureRepository, UInfoRepository infoRepository, RedisUtil redisUtil) {
        this.accountRepository = accountRepository;
        this.secureRepository = secureRepository;
        this.infoRepository = infoRepository;
        this.redisUtil = redisUtil;
    }

//    重写AccountServer的方法
    public boolean Register(String sno, String pwd) {
        UAccount account = accountRepository.findBySno(sno);
        if(account == null) {
            UAccount account1 = new UAccount();
            account1.setSno(sno);
            account1.setPwd(sha3224(pwd));
            account1.setTime(LocalDateTime.now());
            account1.setStatus(0);
            accountRepository.save(account1);
            return true;
        }
        return false;
    }

    public String Login(String sno, String pwd) {
        UAccount account = accountRepository.findBySno(sno);
        if(account != null && account.getPwd().equals(sha3224(pwd))) {

            redisUtil.selectDatabase(6);
            String token = UUID.randomUUID().toString();
            redisUtil.set(sno, token, 1800);
            RecordIP(sno);
//            System.out.println("RecordIP method"); // 添加日志输出
            return token;
        }
        return "Account is not found or pwd is wrong";
    }

    public void Logout(String sno) {
        redisUtil.selectDatabase(6);
        redisUtil.delete(sno);
    }

    public boolean VerifyLogin(String sno, String token) {
        if(redisUtil.get(sno) != null && redisUtil.get(sno).equals(token)) {
            return true;
        }
        return false;
    }

    public boolean isPerfect(String sno) {
        Info info = infoRepository.findBySno(sno);
        if(info != null) {
            return true;
        }
        return false;
    }

    public void perfectInfo(String sno, String name, String sex, String belong, String mail) {

        Info info1 = infoRepository.findBySno(sno);
        if(info1 == null) {
            Info info = new Info();
            info.setSno(sno);
            info.setName(name);
            info.setSex(sex);
            info.setBelong(belong);
            info.setMail(mail);
            info.setTime(LocalDateTime.now());
            infoRepository.save(info);
        }
    }

    public Info getInfo(String sno) {
        return infoRepository.findBySno(sno);
    }

    public void updateInfo(String sno, String name, String belong, String mail) {
        Info info = infoRepository.findBySno(sno);
        info.setSno(sno);
        info.setName(name);
        info.setBelong(belong);
        info.setMail(mail);
        info.setRenewal(LocalDateTime.now());
        infoRepository.save(info);
    }

    public void Delete(String sno) {
        Info info = infoRepository.findBySno(sno);
        UAccount account = accountRepository.findBySno(sno);
        if(info != null) {
            infoRepository.delete(info);
        }
        if(account != null) {
            accountRepository.delete(account);
        }
    }

    public boolean UpdatePwd(String sno, String old, String nuw) {
        UAccount account = accountRepository.findBySno(sno);
        if(account.getPwd().equals(sha3224(old))) {
            account.setPwd(nuw);
            accountRepository.save(account);
            return true;
        }
        return false;
    }


    public void RecordIP(String sno) {
        JSONObject requestData = new JSONObject();
        requestData.put("key", com.example.mass.tencent.TencentMapConstant.KEY);
        String signature = com.example.mass.tencent.TencentMapUtil.generateSignaturePost(com.example.mass.tencent.TencentMapConstant.IP_LOCATION_API, requestData);
        String finalRequest = com.example.mass.tencent.TencentMapConstant.HOST + com.example.mass.tencent.TencentMapConstant.IP_LOCATION_API + "?key=" + com.example.mass.tencent.TencentMapConstant.KEY + "&sig=" + signature;
        System.out.println(finalRequest);

        RestTemplate restTemplate = new RestTemplate();
        com.example.mass.tencent.TencentMapResult tencentMapResult = restTemplate.postForObject(finalRequest, requestData, com.example.mass.tencent.TencentMapResult.class);

        Map<String, Object> resultMap = (Map<String, Object>) tencentMapResult.getResult();
        if (resultMap != null) {
            String ip = (String) resultMap.get("ip");
            Map<String, Double> location = (Map<String, Double>) resultMap.get("location");
            Map<String, String> adInfo = (Map<String, String>) resultMap.get("ad_info");

            // 获取result中的具体值
            Double latitude = location.get("lat");
            Double longitude = location.get("lng");
            String nation = adInfo.get("nation");
            String province = adInfo.get("province");
            String city = adInfo.get("city");
            String district = adInfo.get("district");

            USecure secure = new USecure();
            secure.setSno(sno);
            secure.setIp(ip);
            secure.setLatitude(latitude);
            secure.setLongitude(longitude);
            secure.setNation(nation);
            secure.setProvince(province);
            secure.setCity(city);
            secure.setDistrict(district);
            secure.setTime(LocalDateTime.now());
            secureRepository.save((secure));
        }
    }


    //密码SHA-3加密
    public static String sha3224(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-224");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
