package rbac.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author MiracloW
 */
public class BCryptUtils {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static BCryptPasswordEncoder getInstance(){
        return passwordEncoder;
    }

    private BCryptUtils(){

    }
}
