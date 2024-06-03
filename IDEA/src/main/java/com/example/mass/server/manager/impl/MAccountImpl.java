package com.example.mass.server.manager.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mass.config.RedisUtil;
import com.example.mass.entity.manager.MAccount;
import com.example.mass.entity.manager.MSecure;
import com.example.mass.repository.manager.MAccountRepository;
import com.example.mass.repository.manager.MSecureRepository;
import com.example.mass.server.manager.MAccountServer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class MAccountImpl implements MAccountServer {
    private final MAccountRepository accountRepository;
    private final MSecureRepository secureRepository;
    private final RedisUtil redisUtil;
    public MAccountImpl(MAccountRepository accountRepository, MSecureRepository secureRepository, RedisUtil redisUtil) {
        this.accountRepository = accountRepository;
        this.secureRepository = secureRepository;
        this.redisUtil = redisUtil;
    }

    public String Login(String account, String pwd) {
        MAccount target = accountRepository.findByAccount(account);
        if(target != null && target.getPwd().equals(sha3224(pwd))) {
            String token = UUID.randomUUID().toString();
            redisUtil.selectDatabase(6);
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

    public boolean VerifyLogin(String account, String token) {
        if(redisUtil.get(account) != null && redisUtil.get(account).equals(token)) {
            return true;
        }
        return false;
    }

    public boolean UpdatePwd(String account, String old, String nuw) {
        MAccount target = accountRepository.findByAccount(account);
        if(target.getPwd().equals(sha3224(old))) {
            target.setPwd(nuw);
            accountRepository.save(target);
            return true;
        }
        return false;
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

            MSecure secure = new MSecure();
            secure.setAccount(account);
            secure.setIp(ip);
            secure.setLatitude(latitude);
            secure.setLongitude(longitude);
            secure.setNation(nation);
            secure.setProvince(province);
            secure.setCity(city);
            secure.setDistrict(district);
            secure.setTime(LocalDateTime.now());
            secureRepository.save(secure);
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
