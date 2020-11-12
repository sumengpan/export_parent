package com.smp.web.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class MD5UtilsTest {

    @Test
    public void stringToMD5() {
        String result = MD5Utils.stringToMD5("123");
        System.out.println(result);
        //md5（123）= 202cb962ac59075b964b07152d234b70
        //202cb962ac59075b964b07152d234b70
    }

    //shiro也集成常用的加密的算法md5,sha-1

    @Test
    public void test02() {
        Md5Hash md5Hash = new Md5Hash("123");//参1 传入明文
        System.out.println(md5Hash.toString());
        //202cb962ac59075b964b07152d234b70
    }
    @Test
    public void test03() {
        Md5Hash md5Hash = new Md5Hash("123","lw@export.com");//参1 传入明文  参2盐
        System.out.println(md5Hash.toString());
        //1e99dec7db1120f4604ec224cd90d069
    }
    @Test
    public void test04() {
        Md5Hash md5Hash = new Md5Hash("123","lz@export.com");//参1 传入明文  参2盐
        System.out.println(md5Hash.toString());
        //06ebcfe51319bd9420492f82735e9d99
    }
}
