package cn.ganxy03.xh_website.config;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@Component
public class RedisUtil {
    private static final String REDIS_HOST = "IP";
    private static final int REDIS_PORT = 6379;
    private static final String REDIS_PASSWORD = "password";
    private static Jedis jedis;

    static {
        jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        jedis.auth(REDIS_PASSWORD);
    }
    public static void selectDatabase(int databaseIndex) {
        jedis.select(databaseIndex);
    }
    public static void set(String key, String value, int expireSeconds) {
        jedis.setex(key, expireSeconds, value);
    }
    public static String get(String key) {
        return jedis.get(key);
    }
    public static void delete(String key) {
        jedis.del(key);
    }

    public static String generateToken(String variable) {
        String token = UUID.randomUUID().toString();
        set(variable, token,1800);
        return token;
    }

    public static String getToken(String variable) {
        return jedis.get(variable);
    }

    public static void saveToken(String variable, String token) {
        selectDatabase(3);
        set(variable, token, 1800);
    }

    public static void deleteToken(String variable) {
        selectDatabase(3);
        delete(variable);
    }

    public static void saveMailVerify(String mail, String code) {
        selectDatabase(3);
        set(mail, code, 1800);
    }
}
