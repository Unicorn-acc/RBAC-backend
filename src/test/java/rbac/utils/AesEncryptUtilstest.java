package rbac.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static rbac.utils.AesEncryptUtils.decrypt;
import static rbac.utils.AesEncryptUtils.encrypt;

/**
 * @author MiracloW
 */
public class AesEncryptUtilstest {

    static Logger logger = LoggerFactory.getLogger(AesEncryptUtilstest.class);

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("中文", "汉字");
        String content = JSON.toJSONString(map);

        String KEY = "1234567890123456";

        logger.info("加密前：" + content);

        String encrypt = encrypt(content);
        logger.info("加密后：" + encrypt);

        String decrypt = decrypt(encrypt);
        logger.info("解密后：" + decrypt);
    }
}
