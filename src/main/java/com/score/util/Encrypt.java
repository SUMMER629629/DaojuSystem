package com.score.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

/**
 * @author valor
 * @date 2018/10/7 22:45
 */
public class Encrypt {

    private static final String AES = "AES";

    private static final String ALGORITHM = "SHA1PRNG";

    private static final String WX_ALGORITHM = "AES/CBC/PKCS7Padding";

    private Encrypt() { }

    /**
     * 32位 Hex 小写 md5
     */
    public static String md5HexString(String content) {
        return DigestUtils.md5Hex(content);
    }

    /**
     * 32位 md5 + base64
     */
    public static String md5AndBase64(String content) {
        return base64EncodeToString(DigestUtils.md5(content));
    }

    /**
     * Hex 小写 sha-256
     */
    public static String sha256HexString(String content) {
        return DigestUtils.sha256Hex(content);
    }

    /**
     * sha-256 算法
     */
    public static String sha256AndBase64(String content) {
        return base64EncodeToString(DigestUtils.sha256Hex(content));
    }

    /**
     * base64 加密
     *
     * @param content byte[]
     * @return String
     */
    public static String base64EncodeToString(byte[] content) {
        return Base64.encodeBase64String(content);
    }

    /**
     * base64 加密
     *
     * @param content String
     * @return String
     */
    public static String base64EncodeToString(String content) {
        byte[] bytes = content.getBytes(Charset.forName("utf-8"));
        return base64EncodeToString(bytes);
    }

    /**
     * base64 解密
     *
     * @param content String
     * @return String
     */
    public static String base64DecodeToString(String content) {
        byte[] bytes = base64DecodeToBytes(content);
        return new String(bytes, Charset.forName("utf-8"));
    }

    /**
     * base64 解密
     *
     * @param content String
     * @return byte[]
     */
    public static byte[] base64DecodeToBytes(String content) {
        return Base64.decodeBase64(content);
    }

    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception 抛出异常
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64EncodeToString(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception 抛出异常
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64DecodeToBytes(encryptStr), decryptKey);
    }

    /**
     * AES加密
     * 自定义解密算法
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception 抛出异常
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        SecureRandom random = SecureRandom.getInstance(ALGORITHM);
        random.setSeed(encryptKey.getBytes(Charset.forName("utf-8")));
        kgen.init(128, random);

        return ofEncrypt(AES, content.getBytes(Charset.forName("utf-8")), kgen.generateKey().getEncoded());
    }

    /**
     * AES解密
     * 自定义解密算法
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception 抛出异常
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        SecureRandom random = SecureRandom.getInstance(ALGORITHM);
        random.setSeed(decryptKey.getBytes(Charset.forName("utf-8")));
        kgen.init(128, random);

        byte[] decryptBytes = ofDecrypt(AES, encryptBytes, kgen.generateKey().getEncoded());
        return new String(decryptBytes, Charset.forName("utf-8"));
    }

    /**
     * AES解密
     * 微信aes算法
     *
     * @param encryptedData 待解密的String
     * @param sessionKey    解密密钥
     * @param iv            算法向量
     * @return 解密后的String
     * @throws Exception 抛出异常
     */
    public static String wxAesDecrypt(String encryptedData, String sessionKey, String iv) throws Exception {
        byte[] content = base64DecodeToBytes(encryptedData);
        byte[] keyByte = base64DecodeToBytes(sessionKey);
        byte[] ivByte = base64DecodeToBytes(iv);

        byte[] bytes = ofDecrypt(WX_ALGORITHM, content, keyByte, ivByte);
        return new String(bytes, Charset.forName("utf-8"));
    }

    /**
     * AES加密
     */
    private static byte[] ofEncrypt(String algorithm, byte[] content, byte[] keyByte) throws Exception {
        return doAes(Cipher.ENCRYPT_MODE, algorithm, content, keyByte, null);
    }

    /**
     * AES加密
     */
    private static byte[] ofEncrypt(String algorithm, byte[] content, byte[] keyByte, byte[] ivByte) throws Exception {
        return doAes(Cipher.ENCRYPT_MODE, algorithm, content, keyByte, ivByte);
    }

    /**
     * AES解密
     */
    private static byte[] ofDecrypt(String algorithm, byte[] content, byte[] keyByte) throws Exception {
        return doAes(Cipher.DECRYPT_MODE, algorithm, content, keyByte, null);
    }

    /**
     * AES解密
     */
    private static byte[] ofDecrypt(String algorithm, byte[] content, byte[] keyByte, byte[] ivByte) throws Exception {
        return doAes(Cipher.DECRYPT_MODE, algorithm, content, keyByte, ivByte);
    }

    /**
     * AES算法
     *
     * @param mode      模式 加密/解密
     * @param algorithm 算法填充
     * @param content   待解密的byte[]
     * @param keyByte   解密密钥
     * @param ivByte    算法向量
     * @return 解密后的byte[]
     * @throws Exception 抛出异常
     */
    private static byte[] doAes(int mode, String algorithm, byte[] content, byte[] keyByte, byte[] ivByte) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(algorithm);
        Key sKeySpec = new SecretKeySpec(keyByte, AES);

        if (null != ivByte && 0 != ivByte.length) {
            // 填充iv 向量
            AlgorithmParameters params = AlgorithmParameters.getInstance(AES);
            params.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(mode, sKeySpec, params);
        } else {
            // 初始化
            cipher.init(mode, sKeySpec);
        }

        return cipher.doFinal(content);
    }

}
