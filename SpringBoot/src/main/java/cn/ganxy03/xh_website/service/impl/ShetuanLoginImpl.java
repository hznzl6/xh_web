package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.ShetuanLogin;
import cn.ganxy03.xh_website.antity.UserSecure;
import cn.ganxy03.xh_website.config.RedisUtil;
import cn.ganxy03.xh_website.repository.ShetuanLoginRepository;
import cn.ganxy03.xh_website.repository.UserSecureRepository;
import cn.ganxy03.xh_website.service.ShetuanLoginService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import org.apache.commons.codec.cli.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Enumeration;

@Service
public class ShetuanLoginImpl implements ShetuanLoginService {

    private final ShetuanLoginRepository shetuanLoginRepository;
    private final UserSecureRepository userSecureRepository;

    @Autowired
    public ShetuanLoginImpl(ShetuanLoginRepository shetuanLoginRepository, UserSecureRepository userSecureRepository) {
        this.shetuanLoginRepository = shetuanLoginRepository;
        this.userSecureRepository = userSecureRepository;
    }

    @Override
    public ShetuanLogin RegisterByMail(String sno, String pwd, String mail) {
        ShetuanLogin shetuanLogin = new ShetuanLogin();
        shetuanLogin.setSno(sno);
        shetuanLogin.setPwd(sha3224(pwd));
        shetuanLogin.setMail(mail);
        shetuanLogin.setPerfect(0);
        return shetuanLoginRepository.save(shetuanLogin);
    }

    @Override
    public ShetuanLogin RegisterByPhone(String sno, String pwd, String phone) {
        ShetuanLogin shetuanLogin = new ShetuanLogin();
        shetuanLogin.setSno(sno);
        shetuanLogin.setPwd(sha3224(pwd));
        shetuanLogin.setPhone(phone);
        shetuanLogin.setPerfect(0);
        return shetuanLoginRepository.save(shetuanLogin);
    }

    @Override
    public boolean login(String sno, String pwd, HttpServletRequest request) {
        ShetuanLogin shetuanLogin = shetuanLoginRepository.findBySno(sno);
        String encryptedPwd = sha3224(pwd);
        if(shetuanLogin != null && encryptedPwd.equals(shetuanLogin.getPwd())) {
            UserSecure userSecure = new UserSecure();
            userSecure.setSno(sno);
            userSecure.setLogin_time(LocalDateTime.now());
            userSecure.setLogin_ip(getIPv4Address(request));
            userSecure.setCity(getCityByIPAddress(getIPv4Address(request)));
            userSecureRepository.save(userSecure);
            return true;
        }
        return false;
    }

    @Override
    public ShetuanLogin getInfoBySno(String sno) {
        return shetuanLoginRepository.findBySno(sno);
    }

    @Override
    public boolean updatePassword(String sno, String password) {
        ShetuanLogin shetuanLogin = shetuanLoginRepository.findBySno(sno);
        if (shetuanLogin != null) {
            shetuanLogin.setPwd(sha3224(password));
            shetuanLoginRepository.save(shetuanLogin);
            return true;
        }
        return false;
    }

//    密码MD5加密
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = password.getBytes();
            byte[] hashedBytes = md.digest(passwordBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
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

    public String getIPv4Address(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
//    public static String getIPv4Address() {
//        try {
//            URL url = new URL("https://api.ipify.org"); // 使用ipify.org获取公网IP地址
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            StringBuilder response = new StringBuilder();
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            reader.close();
//            return response.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public static String getIPv4Address() {
//        try {
//            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
//            while (networkInterfaces.hasMoreElements()) {
//                NetworkInterface networkInterface = networkInterfaces.nextElement();
//                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
//                while (inetAddresses.hasMoreElements()) {
//                    InetAddress inetAddress = inetAddresses.nextElement();
//                    if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(':') == -1) {
//                        return inetAddress.getHostAddress();
//                    }
//                }
//            }
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    ip地址查询城市  方法一
    public static String getCityByIPAddress(String ipAddress) {
        try {
            URL url = new URL("http://ip-api.com/json/" + ipAddress); // 使用ip-api.com查询IP地址的城市信息
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            String city = jsonObject.get("city").getAsString();
            return city;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

////    ip地址查询城市  方法二
//    private static final String DATABASE_FILE_PATH = "src/main/resources/GeoLite2-City.mmdb";
//    public static String getCityByIPAddress(String ipAddress) {
//        try {
//            File database = new File(DATABASE_FILE_PATH);
//            DatabaseReader databaseReader = new DatabaseReader.Builder(database).build();
//            InetAddress inetAddress = InetAddress.getByName(ipAddress);
//            CityResponse cityResponse = databaseReader.city(inetAddress);
////            Country country = cityResponse.getCountry();
//            City city = cityResponse.getCity();
//            return city.getName();
//        } catch (IOException | GeoIp2Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}

