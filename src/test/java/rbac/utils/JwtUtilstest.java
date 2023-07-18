package rbac.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rbac.token.UserToken;

/**
 * @author MiracloW
 */
public class JwtUtilstest {

    static Logger logger = LoggerFactory.getLogger(JwtUtilstest.class);

    public static void main(String[] args){
        UserToken userToken = new UserToken();
        userToken.setUserId(1l);
        userToken.setUserName("aaa");

        logger.info("加密前：" + userToken.toString());

        String encrypt = JWTUtils.createToken(userToken);
        logger.info("加密后：" + encrypt);

        UserToken decrypt = JWTUtils.getUserToken(encrypt);
        logger.info("解密后：" + decrypt);

    }
}
