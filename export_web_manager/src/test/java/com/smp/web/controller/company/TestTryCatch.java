package com.smp.web.controller.company;

import org.junit.Test;

public class TestTryCatch {
    @Test
    public void test01(){
        try {
            int num=0;
            System.out.println("Hello");
            System.out.println(1/num);
            System.out.println("World");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
