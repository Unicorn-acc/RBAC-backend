package rbac.utils;

/**
 * @author MiracloW
 */
public class RedisConstants {
    public static final String LOGIN_USER_KEY = "login:";
    public static final Integer LOGIN_USER_TTL = 60 * 30 * 1000; // 30分钟

    private RedisConstants(){

    }
}
