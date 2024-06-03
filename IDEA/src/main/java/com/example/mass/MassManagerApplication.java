package com.example.mass;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MassManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MassManagerApplication.class, args);

        /** post请求示例 */
//        JSONObject requestData = new JSONObject();
//        requestData.put("key", com.example.mass.tencent.TencentMapConstant.KEY);
//
//        String signature = com.example.mass.tencent.TencentMapUtil.generateSignaturePost(com.example.mass.tencent.TencentMapConstant.IP_LOCATION_API, requestData);
//        String finalRequest = com.example.mass.tencent.TencentMapConstant.HOST + com.example.mass.tencent.TencentMapConstant.IP_LOCATION_API + "?key=" + com.example.mass.tencent.TencentMapConstant.KEY + "&sig=" + signature;
//        System.out.println(finalRequest);
//        RestTemplate restTemplate = new RestTemplate();
//        System.out.println(restTemplate.postForObject(finalRequest, requestData, com.example.mass.tencent.TencentMapResult.class));


    }

}
