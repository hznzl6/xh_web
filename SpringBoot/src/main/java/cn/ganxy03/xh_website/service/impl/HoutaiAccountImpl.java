package cn.ganxy03.xh_website.service.impl;

import cn.ganxy03.xh_website.antity.HoutaiAccount;
import cn.ganxy03.xh_website.antity.HoutaiSecure;
import cn.ganxy03.xh_website.repository.HoutaiAccountRepository;
import cn.ganxy03.xh_website.repository.HoutaiSecureReposotory;
import cn.ganxy03.xh_website.service.HoutaiAccountService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class HoutaiAccountImpl implements HoutaiAccountService {

    private final HoutaiAccountRepository houtaiAccountRepository;
    private final HoutaiSecureReposotory houtaiSecureReposotory;

    public HoutaiAccountImpl(HoutaiAccountRepository houtaiAccountRepository, HoutaiSecureReposotory houtaiSecureReposotory) {
        this.houtaiAccountRepository = houtaiAccountRepository;
        this.houtaiSecureReposotory = houtaiSecureReposotory;
    }

    @Override
    public void CreateAccount(String role) {
        HoutaiAccount houtaiAccount = new HoutaiAccount();
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(9);
        String account = "hnld" + random.nextInt(100000);
        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        String pwd = sb.toString();
        houtaiAccount.setAccount(account);
        houtaiAccount.setPwd(pwd);
        houtaiAccount.setRole(role);
        houtaiAccount.setCreate_time(LocalDateTime.now());
        houtaiAccountRepository.save(houtaiAccount);
    }

    @Override
    public void ChangeRole(String account, String role) {
        HoutaiAccount houtaiAccount = houtaiAccountRepository.findByAccount(account);
        if (houtaiAccount != null) {
            houtaiAccount.setRole(role);
            houtaiAccount.setUpdate_time(LocalDateTime.now());
            houtaiAccountRepository.save(houtaiAccount);
        }
    }

    @Override
    public void DeleteAccount(String account) {
        HoutaiAccount houtaiAccount = houtaiAccountRepository.findByAccount(account);
        if (houtaiAccount != null) {
            houtaiAccount.setStatus(1);
            houtaiAccountRepository.save(houtaiAccount);
        }
    }

    @Override
    public void InitPwd(String account) {
        HoutaiAccount houtaiAccount = houtaiAccountRepository.findByAccount(account);
        if (houtaiAccount != null) {
            Random random = new Random();
            String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder sb = new StringBuilder(9);
            for (int i = 0; i < 9; i++) {
                int index = random.nextInt(characters.length());
                sb.append(characters.charAt(index));
            }
            String pwd = sb.toString();
            houtaiAccount.setPwd(pwd);
            houtaiAccount.setUpdate_time(LocalDateTime.now());
            houtaiAccountRepository.save(houtaiAccount);
        }
    }

    @Override
    public void ChangePwd(String account, String pwd) {
        HoutaiAccount houtaiAccount = houtaiAccountRepository.findByAccount(account);
        if (houtaiAccount != null) {
            houtaiAccount.setPwd(pwd);
            houtaiAccount.setUpdate_time(LocalDateTime.now());
            houtaiAccountRepository.save(houtaiAccount);
        }
    }

    @Override
    public Integer Login(String account, String pwd, HttpServletRequest request) {
        HoutaiAccount houtaiAccount = houtaiAccountRepository.findByAccountAndPwd(account, pwd);
        if (houtaiAccount != null) {
            Integer status = houtaiAccount.getStatus();
            if (status != null && status.equals(1)) {
                return 1;
            }
            HoutaiSecure houtaiSecure = new HoutaiSecure();
            houtaiSecure.setAccount(account);
//            houtaiSecure.setLogin_ip(request.getRemoteAddr());
            houtaiSecure.setLogin_ip(getIPv4Address(request));
            houtaiSecure.setLogin_time(LocalDateTime.now());
            houtaiSecureReposotory.save(houtaiSecure);
            return 2;
        } else {
            return 3;
        }
    }

    private String getIPv4Address(HttpServletRequest request) {
        try {
            InetAddress inetAddress = InetAddress.getByName(request.getRemoteAddr());
            if (inetAddress instanceof Inet6Address) {
                // 如果是 IPv6 地址
                byte[] ipv6Bytes = inetAddress.getAddress();
                // 将 IPv6 地址转换为 IPv4 地址的字节数组
                byte[] ipv4Bytes = new byte[4];
                ipv4Bytes[0] = ipv6Bytes[12];
                ipv4Bytes[1] = ipv6Bytes[13];
                ipv4Bytes[2] = ipv6Bytes[14];
                ipv4Bytes[3] = ipv6Bytes[15];
                // 将 IPv4 地址字节数组转换为字符串形式
                String ipv4Address = String.format("%d.%d.%d.%d",
                        ipv4Bytes[0] & 0xff, ipv4Bytes[1] & 0xff, ipv4Bytes[2] & 0xff, ipv4Bytes[3] & 0xff);
                return ipv4Address;
            } else if (inetAddress instanceof Inet4Address) {
                // 如果是IPv4地址，则直接返回Ipv4格式的字符串
                return inetAddress.getHostAddress();
            }
        } catch (UnknownHostException e) {
            // 处理异常情况，例如无法解析IP地址等
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HoutaiAccount GetInfo(String account) {
        HoutaiAccount houtaiAccount = houtaiAccountRepository.findByAccount(account);
        return houtaiAccount;
    }
}
