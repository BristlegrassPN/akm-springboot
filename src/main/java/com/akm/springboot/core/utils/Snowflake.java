package com.akm.springboot.core.utils;

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
        System.out.println(Snowflake.uuid());
        System.out.println(Snowflake.uuid());
        System.out.println(Snowflake.uuid());
        System.out.println(Snowflake.uuid());
    }
}
