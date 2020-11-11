package com.smp.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class CompanyProvider {
    public static void main(String[] args) throws IOException {
        //调用start
        new ClassPathXmlApplicationContext();
        //阻塞方法
        //1.加载配置文件
        ClassPathXmlApplicationContext
                cxt = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        //2.启动
        cxt.start();
        //3.阻塞
        System.in.read();
    }
}
