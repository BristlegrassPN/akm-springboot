package com.akm.springboot.core.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.spec.PKCS8EncodedKeySpec;

public class TestApi {

    static void rsaDecrypt() {
        String bodyStr = "U1ZmQ1FIcFZsaUw2eXdza3JUcUQ2UGJyVk5ndlRMRm5YckhhSzZLT3djN3k0dUMvMUlqV21VajQ1Nm1JMzlDWE5Ha3VCejJlQk81UTRkdncwazhtSXUxY3BoNkFZWFRNZEZlcXowUUUvYUlKbEVrb0JsaGYxN2lHWTlzSGdGUFphWmlkYUlMaU1odnd0NnNhZjROZ2NUSExhU3NFTjNaNFpSeEZibG8zRFhvPQ==";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI3UsvdiazOYaN2aI7pu27zCgEhuywLpPxyQss3UhDIwlY3eSU/12XB77HHvZ4hv0RHnPeQlQd1jNtRy6NXOq3yM1dcRp+KKe438LbjoGPm87GtnEOeVzKehZL8A8BwzP+I0p3/1Oz/gyjVDu+DmfzFUqrqNzcO1CYg5RXzv6lO7AgMBAAECgYEAg3VKDBGk20XArOVZiEuIUATXckt+z0vxu0BC+pSnkv2FIOGPghWVXLUACvqjM8qwNa/npbcCvXhgMbnwJXxRZM5f2n5CywagZSt2IJNWZ92pTG1WuFcy250Sg3HatV53G8Y+cnCsjTrYL0l3WFeMiCLHMwq4J9PsB/AvlbI5PEECQQDs7BUCCrBUKd84e9+FKUTosvhhZvL24S9UkD0XPzi4MmXBa0tOrAnHhbqJAtmQ0aC1j1RpS/7zCeUaonlTlBCnAkEAmUBnEkn7QBUDTloJoHiTq3UvF3PI7z/y0lm23HLbFKP7TFGCxci0ZgxNASjjZhxJhNh7vvoNzTWh/UK7c9DSzQJBANiskAnt/JT8NklSAHmn5H1sL/vkSw+9oauUvkokZ5sNfEIH/jdU3xy6EH59PLxU+sUQ8AHcnq1mv0A8lvj40MECQGPsDQhsumh3CvfMVxiNZeNWfcTx1qLAdGfY3RTxwoy7ur9xKi4hhhY/kPnfmh1a9kirCkxkKBmUDbHfD/AcyUUCQFxz6XWMmcT+Ad7HaXCel9OFQqfRYZOROEXdFA9WExYc5vPR6sQc+3uPbtSMISOMSblet9e3QoO3OJ8zfhT2yf4=";
        try {
            // 设置私钥，其中PKCS8EncodedKeySpec是java jdk自带的工具类；我这里用的是jdk 1.8
            byte[] keyBytes = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

            // 由于bodyStr在前端用base64加密过，这里先用base64解密
            String content = new String(Base64.decodeBase64(bodyStr), "utf-8");

            // 用私钥解密。得到内容
            byte[] data = Base64.decodeBase64(content);
            String decryptBodyStr = new String(RSAUtils.decryptByPkcs(data, pkcs8KeySpec), "UTF-8");
            System.out.println("ras result:" + decryptBodyStr);

            // 把内容用json工具转为对象或java bean
//            Map<String, Object> map = JacksonUtils.instance().json2map(decryptBodyStr);
//            User user = JacksonUtils.instance().json2pojo(decryptBodyStr, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void aesEncryptAndDecrypt() {
        // 待加密的内容，实际环境会把对象转为json字符串进行加密
        String str = "abc123!@#ggg*&^/sdf（）*&地方ds%^9865的";
        String secretKey = "6efb1aa21b100d4b";
        try {
            String encryptStr = AESUtils.encrypt(str, secretKey);
            String result = new String(Base64.encodeBase64(encryptStr.getBytes()));
            System.out.println("aes result:" + result);

            // base64解码
            String content = new String(Base64.decodeBase64(result), "utf-8");
            // aes解密
            String decryptStr = AESUtils.decrypt(content, secretKey);
            System.out.println("aes result:" + decryptStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TestApi.rsaDecrypt();
        TestApi.aesEncryptAndDecrypt();
    }

}
