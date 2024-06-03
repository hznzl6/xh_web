package com.example.mass.tencent;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class TencentMapUtil {
    /**
     * 签名计算(sig)_GET：
     * @param path  请求路径
     * @param params    请求参数
     * @return
     */
    public static String generateSignatureGet(String path, Map<String, String> params) {
        try {
            //将参数按照 key 进行字典序排序
            TreeMap<String, String> sortedParams = new TreeMap<>(params);
            //构建原始签名字符串
            StringBuilder rawSignatureBuilder = new StringBuilder();
            rawSignatureBuilder.append(path).append("?");
            int entryIndex = 0;
            for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
                rawSignatureBuilder.append(entry.getKey()).append("=").append(entry.getValue());
                if (entryIndex < sortedParams.size() - 1) {
                    rawSignatureBuilder.append("&");
                }
                entryIndex++;
            }
            rawSignatureBuilder.append(TencentMapConstant.SK);
            String rawSignature = rawSignatureBuilder.toString();

            // 计算 MD5 签名
            byte[] signatureBytes = MessageDigest.getInstance("MD5").digest(rawSignature.getBytes("UTF-8"));
            String signature = byteArrayToHexString(signatureBytes);

            return URLEncoder.encode(signature, "UTF-8"); // 进行 URL 编码
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("签名计算失败: {}", e.getMessage(), e);
            return null;
        }
    }
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    /**
     * 对参数进行编码
     * @param params
     * @return
     */
    public static String encodeParams(Map<String, String> params) {
        TreeMap<String, String> sortedParams = new TreeMap<>(params);
        StringBuilder rawSignatureBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            rawSignatureBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return rawSignatureBuilder.toString();
    }

    /**
     * 签名计算(sig)_POST：
     * @param jsonObject
     */
    public static String generateSignaturePost(String path, JSONObject jsonObject) {
        //一级属性名排序字符升序排序
        Set<String> propertyNames = jsonObject.keySet();
        List<String> sortedPropertyNames = new ArrayList<>(propertyNames);
        Collections.sort(sortedPropertyNames);
        JSONObject sortedJsonObject = new JSONObject();
        for (String propertyName : sortedPropertyNames) {
            //  Value转成JSON string
//            sortedJsonObject.put(propertyName, JSONUtil.toJsonStr(jsonObject.get(propertyName)));
            sortedJsonObject.put(propertyName, jsonObject.get(propertyName).toString());
        }
        //替换原始的 JSONObject
        jsonObject.clear();
        jsonObject.putAll(sortedJsonObject);

        //拼接成rawSignatureBuilder型的字符串
        StringBuilder rawSignatureBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String propertyName = entry.getKey();
            String propertyValue = (String) entry.getValue();
            // 拼接成 rawSignatureBuilder 型的字符串
            rawSignatureBuilder.append(propertyName)
                    .append("=")
                    .append(propertyValue)
                    .append("&");
        }
        //去除末尾的"&"
        if (rawSignatureBuilder.length() > 0) {
            rawSignatureBuilder.deleteCharAt(rawSignatureBuilder.length() - 1);
        }

        //签名计算(sig) 请求路径+”?”+请求参数+SK
        rawSignatureBuilder.insert(0, path + "?").append(TencentMapConstant.SK);
        String rawSignature = rawSignatureBuilder.toString();

        // 计算 MD5 签名
        try {
            byte[] signatureBytes = MessageDigest.getInstance("MD5").digest(rawSignature.getBytes("UTF-8"));
            String signature = byteArrayToHexString(signatureBytes);

            return URLEncoder.encode(signature, "UTF-8"); // 进行 URL 编码
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("签名计算失败: {}", e.getMessage(), e);
        }
        return null;
    }
}
