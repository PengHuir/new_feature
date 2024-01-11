package com.echo.feature.boot.utils;

import com.echo.feature.boot.utils.misc.BASE64Decoder;
import com.echo.feature.boot.utils.misc.BASE64Encoder;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * NAME: AESUtil
 * Description: AES加密工具
 * Author: zhangph
 * Date: 2023/12/29 10:59
 */
@Slf4j
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public AESUtil() {
    }

    public static String encrypt(String data, AesSkParam skParam) throws Exception {
        byte[] encrypt;
        if (skParam.getCipherAlgorithm() != null && !skParam.getCipherAlgorithm().isEmpty()) {
            encrypt = AESUtil.encrypt(data.getBytes(), AESUtil.base64Decode(skParam.getSecretKey()), skParam.getCipherAlgorithm());
            return AESUtil.base64Encode(encrypt);
        } else {
            encrypt = AESUtil.encrypt(data.getBytes(), AESUtil.base64Decode(skParam.getSecretKey()));
            return AESUtil.base64Encode(encrypt);
        }
    }

    public static String decrypt(String data, AesSkParam skParam) throws Exception {
        byte[] decrypt;
        if (skParam.getCipherAlgorithm() != null && !skParam.getCipherAlgorithm().isEmpty()) {
            decrypt = AESUtil.decrypt(AESUtil.base64Decode(data), AESUtil.base64Decode(skParam.getSecretKey()), skParam.getCipherAlgorithm());
            return new String(decrypt);
        } else {
            decrypt = AESUtil.decrypt(AESUtil.base64Decode(data), AESUtil.base64Decode(skParam.getSecretKey()));
            return new String(decrypt);
        }
    }

    private static byte[] getSecretKey(String key) throws NoSuchAlgorithmException {
        try {
            KeyGenerator keyGenerator;
            if (key != null && key.isEmpty()) {
                keyGenerator = KeyGenerator.getInstance("AES");
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                random.setSeed(key.getBytes());
                keyGenerator.init(128, random);
                SecretKey secretKey = keyGenerator.generateKey();
                return secretKey.getEncoded();
            } else {
                keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(128);
                SecretKey secretKey = keyGenerator.generateKey();
                return secretKey.getEncoded();
            }
        } catch (NoSuchAlgorithmException var4) {
            log.error(var4.getMessage(), var4);
            return null;
        }
    }

    private static Key toKey(byte[] secretKey) {
        return new SecretKeySpec(secretKey, "AES");
    }

    private static byte[] encrypt(byte[] data, Key key) throws Exception {
        try {
            return encrypt(data, key, "AES/ECB/PKCS5Padding");
        } catch (Throwable var3) {
            log.error(var3.getMessage(), var3);
            throw var3;
        }
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        try {
            return encrypt(data, key, "AES/ECB/PKCS5Padding");
        } catch (Throwable var3) {
            log.error(var3.getMessage(), var3);
            throw var3;
        }
    }

    private static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        try {
            Key k = toKey(key);
            return encrypt(data, k, cipherAlgorithm);
        } catch (Throwable var4) {
            log.error(var4.getMessage(), var4);
            throw var4;
        }
    }

    private static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(1, key);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException var4) {
            log.error(var4.getMessage(), var4);
            throw var4;
        } catch (NoSuchPaddingException var5) {
            log.error(var5.getMessage(), var5);
            throw var5;
        } catch (InvalidKeyException var6) {
            log.error(var6.getMessage(), var6);
            throw var6;
        } catch (IllegalBlockSizeException var7) {
            log.error(var7.getMessage(), var7);
            throw var7;
        } catch (BadPaddingException var8) {
            log.error(var8.getMessage(), var8);
            throw var8;
        }
    }

    private static byte[] decrypt(byte[] data, Key key) throws Exception {
        try {
            return decrypt(data, key, "AES/ECB/PKCS5Padding");
        } catch (Throwable var3) {
            log.error(var3.getMessage(), var3);
            throw var3;
        }
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        try {
            return decrypt(data, key, "AES/ECB/PKCS5Padding");
        } catch (Throwable var3) {
            log.error(var3.getMessage(), var3);
            throw var3;
        }
    }

    private static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        try {
            Key k = toKey(key);
            return decrypt(data, k, cipherAlgorithm);
        } catch (Throwable var4) {
            log.error(var4.getMessage(), var4);
            throw var4;
        }
    }

    private static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(2, key);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException var4) {
            log.error(var4.getMessage(), var4);
            throw var4;
        } catch (NoSuchPaddingException var5) {
            log.error(var5.getMessage(), var5);
            throw var5;
        } catch (InvalidKeyException var6) {
            log.error(var6.getMessage(), var6);
            throw var6;
        } catch (IllegalBlockSizeException var7) {
            log.error(var7.getMessage(), var7);
            throw var7;
        } catch (BadPaddingException var8) {
            log.error(var8.getMessage(), var8);
            throw var8;
        }
    }

    private static String base64Encode(byte[] bytes) {
        return (new BASE64Encoder()).encodeBuffer(bytes);
    }

    private static byte[] base64Decode(String s) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(s);
    }

    public static class AesSkParam {

        private String secretKey;
        private String cipherAlgorithm;

        public AesSkParam() {
        }

        public String getSecretKey() {
            return this.secretKey;
        }

        public String getCipherAlgorithm() {
            return this.cipherAlgorithm;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public void setCipherAlgorithm(String cipherAlgorithm) {
            this.cipherAlgorithm = cipherAlgorithm;
        }
    }

    public static void main(String[] args) {
        String str = "{\"serialNum\":\"Q6d524da5a30f4a97a4f\",\"xsfnsrsbh\":\"91320113MAD5NB6E34\",\"companyName\":\"南京云合慧联信息科技有限公司\",\"account\":\"17625212308\",\"name\":\"陈诚\",\"loginStatus\":\"00\",\"authMethod\":\"01\",\"operType\":\"02\",\"latestDate\":\"2023-12-28 11:29:01\",\"createTime\":\"2023-12-28 14:53:16\"}";

        AesSkParam aesSkParam = new AesSkParam();
        aesSkParam.setSecretKey("12345ACDEFrw/123BLaiZQ==");
        aesSkParam.setCipherAlgorithm("AES/ECB/PKCS5Padding");

        try {
            String encrypt = AESUtil.encrypt(str, aesSkParam);
            System.out.println("解密后的内容: " + encrypt);

            String decrypt = AESUtil.decrypt(encrypt, aesSkParam);
            System.out.println("解密后的内容: " + decrypt);

            System.out.println(decrypt.equals(str));
        } catch (Throwable var7) {
            var7.printStackTrace();
        }
    }
}
