package com.akm.springboot.core.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;

public class Snowflake {

    private volatile static cn.hutool.core.lang.Snowflake snowflake;

    private Snowflake() {
    }

    public static String uuid() {
        if (snowflake == null) {
            synchronized (cn.hutool.core.lang.Snowflake.class) {
                if (snowflake == null) {
                    snowflake = new cn.hutool.core.lang.Snowflake(1, 1);
                }
            }
        }
        return String.valueOf(snowflake.nextId());
    }

    public static void main(String[] args) {
//        System.out.println(Snowflake.uuid());
//        System.out.println(Snowflake.uuid());
//        System.out.println(Snowflake.uuid());
//        System.out.println(Snowflake.uuid());
        String id = IdUtil.simpleUUID();

        String str = "d109da0b08214b1bb9e63bfab8ad24f1";
        String p = "d109da0b08214b1bb9e63bfab8ad24f1";
        String username = "d109da0b08214b1bb9e63bfab8ad24f1";

        String password = SecureUtil.md5(SecureUtil.sha256(str) + SecureUtil.sha256(p) + SecureUtil.sha256(username));
        System.out.println(password);
        System.out.println(SecureUtil.sha256(str));

//        System.out.println(HashUtil.fnvHash("d109da0b08214b1bb9e63bfab8ad24f1"));
    }
}
