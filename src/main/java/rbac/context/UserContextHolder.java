package rbac.context;

import rbac.token.UserToken;

/**
 * Threadlocal保存当前登录的用户信息token
 * @author MiracloW
 */
public class UserContextHolder {

    private static ThreadLocal<UserToken> threadLocal = new ThreadLocal<>();

    public static void set(UserToken userToken){
        threadLocal.set(userToken);
    }

    public static UserToken get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }

    private UserContextHolder(){

    }
}
