package rbac.utils;

import org.apache.commons.codec.binary.Base64;
import rbac.common.enums.ExceptionEnum;
import rbac.common.exception.CustomException;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author MiracloW
 */
public class RsaEncryptUtils {

    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgoitkymsW4wuxcroQwUgkvd43Qx+CSgxgJIUs6Mzm9oN4+zMxBz3VMiPfF1wV8OWMXwMylnGf2GoXkco9+ucAwErXfja8gjyUdRAv3nX1COhWllmQ14MPhkedKivmreF7cFtwYOWSCwh8oLJKnvV3GzA8hICeKS3YKRkDgntr7wIDAQAB";

    private static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOCiK2TKaxbjC7FyuhDBSCS93jdDH4JKDGAkhSzozOb2g3j7MzEHPdUyI98XXBXw5YxfAzKWcZ/YaheRyj365wDAStd+NryCPJR1EC/edfUI6FaWWZDXgw+GR50qK+at4XtwW3Bg5ZILCHygskqe9XcbMDyEgJ4pLdgpGQOCe2vvAgMBAAECgYBg8hxAN4vYKMzXHGho6sGz+a15tCJZHJQSujAvnZkw5JsBD4icVD+/TxBkK7S2U2z+E9NRddQ75C6FVYovDaW7wkOzjmTnbgTm8Pq2aAlYSH/YKm/pFI1zlbl31KJYbnEzRAU2VwoC1kMJQLHg8iinlKwW/qOr/gs3CJ9YFrk/IQJBAPDm7tqX8t/BZQ8ZMk+WybT3m8wRrBjT8sS5BZab1Der6ydlUvew1kjJCT5WVdDoh8bzHpFLPLlIMk5rOD9iom0CQQDutjh8loQ9r4capSNnHyzneKui2o+pJ+NqgzW7zVswW0Xf0ezuZesAuCjzZrGukxeKOOJVTDzkOnbvXb1zF25LAkBHiezIkXMYq4/dSfg81mSUDyv/EAhxXw+rB972+NOurKkqi9Z1o9geyUSrdGwLm+ImXLBpKVShGSWHUCPl/t6JAkEAqkoTwc0eSWMOXlOwDcCPqBDNYD/KfEGYWkN/8Zltk/GIx0JaYzcRjlHR5NE9r+v+vvwVegQI7EdI0TluDMyKQQJBAKBMVXNYqy5Xw3dNFOo104MnKlXxsGDWEn94DHD6ivTaHt5wijRH2y1MrAeUND0EjgldfYN2Bp1tpG1ZiHjcdyY=";

    private static final String ALGO = "RSA/ECB/OAEPWithSHA-1AndMGF1PADDING";

    /**
     * 私钥解密
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String text) {
        try{
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(Base64.decodeBase64(text));
            return new String(result);
        }catch (Exception e){
            throw new CustomException(ExceptionEnum.DECODE_ERROR);
        }
    }

    /**
     * 公钥加密
     *
     * @param publicKeyText
     * @param text
     * @return
     */
    public static String encryptByPublicKey(String publicKeyText, String text){
        try{
            X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyText));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(text.getBytes());
            return Base64.encodeBase64String(result);
        }catch (Exception e){
            throw new CustomException(ExceptionEnum.ENCODE_ERROR);
        }
    }

    public static String encrypt(String text){
        return encryptByPublicKey(publicKey, text);
    }

    public static String decrypt(String text){
        return decryptByPrivateKey(text);
    }

    private RsaEncryptUtils(){
    }

    /**
     * RSA密钥对对象
     */
    public static class RSAKeyPair {

        private String publicKey;
        private String privateKey;

        public RSAKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

    }
}