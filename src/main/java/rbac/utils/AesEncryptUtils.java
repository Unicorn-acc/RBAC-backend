package rbac.utils;


import org.apache.commons.codec.binary.Hex;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Set;

/**
 * AES加密工具类
 *
 */
public class AesEncryptUtils {

    private static final String KEY = "AES_SECRET_KEY";

    private static final String ALGO = "AES_256/GCM/NOPADDING";

    public static final int AES_KEY_SIZE = 256;

    public static final int GCM_IV_LENGTH = 12;

    public static final int TLEN = 128;


    static {
        Set<String> algorithms = Security.getAlgorithms("Cipher");
        if (!algorithms.contains(ALGO)) {
            throw new IllegalArgumentException("AES256加解密系统不可用");
        }
    }

    public static String encrypt(String txt, String pwd) {
        try{
            Cipher cipher = Cipher.getInstance(ALGO);
            byte[] iv = new byte[GCM_IV_LENGTH];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(pwd), new GCMParameterSpec(TLEN, iv));

            //iv和加密后的字节数组进行组合（用于解密时获取iv和加密内容）
            byte[] txtBytes = txt.getBytes(StandardCharsets.UTF_8);
            byte[] enctyptBytes = cipher.doFinal(txtBytes);
            byte[] message = new byte[GCM_IV_LENGTH + enctyptBytes.length];
            System.arraycopy(iv, 0, message, 0, GCM_IV_LENGTH);
            System.arraycopy(enctyptBytes, 0, message, GCM_IV_LENGTH, enctyptBytes.length);

            return Hex.encodeHexString(message);
        }catch (Exception e){
            throw new CustomException(ExceptionEnum.ENCODE_ERROR);
        }
    }

    public static String decrypt(String txt, String pwd)  {
        try{
            //分离iv和加密内容
            byte[] txtBytes = Hex.decodeHex(txt.toCharArray());
            byte[] iv = new byte[GCM_IV_LENGTH];
            byte[] content = new byte[txtBytes.length - GCM_IV_LENGTH];
            System.arraycopy(txtBytes, 0, iv, 0, GCM_IV_LENGTH);
            System.arraycopy(txtBytes, GCM_IV_LENGTH, content, 0, content.length);

            Cipher cipher = Cipher.getInstance(ALGO);
            GCMParameterSpec params = new GCMParameterSpec(TLEN, iv);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(pwd), params);

            return new String(cipher.doFinal(content), StandardCharsets.UTF_8);
        }catch (Exception e){
            throw new CustomException(ExceptionEnum.DECODE_ERROR);
        }
    }

    public static Key generateKey(String keystr) {
        try{
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
            secureRandom.setSeed(keystr.getBytes(StandardCharsets.UTF_8));
            kg.init(AES_KEY_SIZE, secureRandom);

            return kg.generateKey();
        }catch (Exception e){
            throw new CustomException(ExceptionEnum.DECODE_ERROR);
        }
    }

    public static String encrypt(String content){
        return encrypt(content, KEY);
    }

    public static String decrypt(String content){
        return decrypt(content, KEY);
    }

    private AesEncryptUtils(){
        throw new IllegalStateException("Utility class");
    }

}