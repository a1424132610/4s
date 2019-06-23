package com.fours.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {

    //加密方式
    public static final String ALGORITHMNAME = "MD5";
    //盐值
    public static final String SALT = "itsource";
    //加密次数
    public static final int HASHITERATIONS = 10;



    /**
     * String algorithmName  加密方式
     * Object source    对源数据进行加密
     */
    public static String createMd5(String source){
        SimpleHash hash = new SimpleHash(ALGORITHMNAME, source, SALT, HASHITERATIONS );
        return hash.toString();
    }

}
