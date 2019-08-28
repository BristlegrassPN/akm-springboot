package com.akm.springboot.core.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AESUtils {

    // 加密算法 AES
    private static final String KEY_ALGORITHM = "AES";
    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHM_STR = "AES/CBC/PKCS5Padding";
    // AES supports 128, 192 and 256 bit keys, so the number of bytes needs to be 16, 24, or 32
    private static final int INIT_SIZE = 128;

    private static final String CHARSET = "utf-8";

// PKCS7Padding
//    static {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//    }

    /**
     * 加密
     *
     * @param content 加密的字符串
     * @param key     key值
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key) throws Exception {
        if (key.length() != 16) {
            throw new Exception("ASE密钥格式不对");
        }
        KeyGenerator gen = KeyGenerator.getInstance(KEY_ALGORITHM);
        gen.init(INIT_SIZE);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        //iv 与key一样
        byte[] keyBytes = key.getBytes(CHARSET);
        byte[] ivBytes = key.getBytes(CHARSET);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM), new IvParameterSpec(ivBytes));
        byte[] b = cipher.doFinal(content.getBytes(CHARSET));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     *
     * @param encryptStr 解密的字符串
     * @param key        解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String key) throws Exception {
        if (key.length() != 16) {
            throw new Exception("ASE密钥格式不对");
        }
        KeyGenerator gen = KeyGenerator.getInstance(KEY_ALGORITHM);
        gen.init(INIT_SIZE);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        byte[] keyBytes = key.getBytes(CHARSET);
        byte[] ivBytes = key.getBytes(CHARSET);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, KEY_ALGORITHM), new IvParameterSpec(ivBytes));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }


    public static void main(String[] args) {
        String key = "fa9353c6179dfbfa";
        try {
            String s = encrypt("123423", key);
            System.out.println(s);
            String ss = decrypt(s, key);
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Base64 base64 = new Base64();
        String str = "*&^&*%^&%$$#@!@@!&^*(/";
        byte[] b = str.getBytes();
        String encode = base64.encodeToString(b);
        String encode2 = new String(Base64.encodeBase64(b));
        System.out.println("encode:" + encode);
        try {
            System.out.println("decodeBase64:" + Base64.decodeBase64(str));
            System.out.println("decode:" + base64.decode(encode));
            System.out.println("decode:" + new String(base64.decode(encode), "UTF-8"));
            System.out.println("decode:" + new String(base64.decode(encode2), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
