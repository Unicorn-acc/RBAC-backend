package rbac.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static rbac.utils.RsaEncryptUtils.decrypt;
import static rbac.utils.RsaEncryptUtils.encrypt;


/**
 * @author MiracloW
 */
public class RsaEncryptUtilstest {
    static Logger logger = LoggerFactory.getLogger(RsaEncryptUtilstest.class);

    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("key", "value");
//        map.put("中文", "汉字");
//        String content = JSON.toJSONString(map);
        String content = "123456";

        logger.info("加密前：" + content);

        String encrypt = encrypt(content);
        logger.info("加密后：" + encrypt);

        String decrypt = decrypt(encrypt);
        logger.info("解密后：" + decrypt);
    }
}
