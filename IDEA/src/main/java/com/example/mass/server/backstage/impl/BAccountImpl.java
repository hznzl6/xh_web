package com.example.mass.server.backstage.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mass.config.RedisUtil;
import com.example.mass.entity.backstage.BAccount;
import com.example.mass.entity.backstage.BSecure;
import com.example.mass.entity.user.USecure;
import com.example.mass.repository.backstage.BAccountRepository;
import com.example.mass.repository.backstage.BSecureRepository;
import com.example.mass.server.backstage.BAccountServer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class BAccountImpl implements BAccountServer {
    private final BAccountRepository bAccountRepository;
    private final BSecureRepository bSecureRepository;
    private final RedisUtil redisUtil;

    public BAccountImpl(BAccountRepository bAccountRepository, RedisUtil redisUtil, BSecureRepository bSecureRepository) {
        this.bAccountRepository = bAccountRepository;
        this.redisUtil = redisUtil;
        this.bSecureRepository = bSecureRepository;
    }

    public String Login(String account, String pwd) {
        BAccount bAccount = bAccountRepository.findByAccount(account);
        if(bAccount != null && bAccount.getPwd().equals(sha3224(pwd))) {
            redisUtil.selectDatabase(6);
            String token = UUID.randomUUID().toString();
            redisUtil.set(account, token, 1800);
            RecordIP(account);
            return token;
        }
        return "Account is not found or pwd is wrong";
    }

    public void Logout(String account) {
        redisUtil.selectDatabase(6);
        redisUtil.delete(account);
    }

    public boolean Verify(String account, String token) {
        if(redisUtil.get(account) != null && redisUtil.get(account).equals(token)) {
            return true;
        }
        return false;
    }

    public boolean UpdatePwd(String account, String old, String nuw) {
        BAccount bAccount = bAccountRepository.findByAccount(account);
        if (bAccount.getPwd().equals(sha3224(old))) {
            bAccount.setPwd(nuw);
            bAccountRepository.save(bAccount);
            return true;
        }
        return false;
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

    public void RecordIP(String account) {
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

            BSecure secure = new BSecure();
            secure.setAccount(account);
            secure.setIp(ip);
            secure.setLatitude(latitude);
            secure.setLongitude(longitude);
            secure.setNation(nation);
            secure.setProvince(province);
            secure.setCity(city);
            secure.setDistrict(district);
            secure.setTime(LocalDateTime.now());
            bSecureRepository.save((secure));
        }
    }
}
