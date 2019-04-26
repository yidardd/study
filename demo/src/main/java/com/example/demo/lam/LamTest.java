package com.example.demo.lam;

import java.math.BigDecimal;
import java.nio.file.Files;

/**
 * Created by 东东 on 2019/4/8.
 */
public class LamTest {

    public static void main(String[] args) {
//        System.out.println(0.1 + 0.1);
//        System.out.println(0.1 + 0.2);
//        System.out.println(0.1 + 0.7);
//        System.out.println(3 * 0.1);
//        System.out.println(3 * 0.1 == 0.3F);
//        test();
        test1();

    }

    public static void test() {
//        Files.copy()
        BigDecimal bigDecimal1 = new BigDecimal(0.1);
        BigDecimal bigDecimal2 = new BigDecimal(0.2);
        BigDecimal bigDecimal3 = new BigDecimal(0.3);
        BigDecimal bigDecimal7 = new BigDecimal(0.7);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
        System.out.println(bigDecimal3);
        System.out.println(bigDecimal7);
        System.out.println(bigDecimal1.add(bigDecimal1));
        System.out.println(bigDecimal1.add(bigDecimal2));
        System.out.println(bigDecimal1.add(bigDecimal7));
        System.out.println(bigDecimal1.multiply(bigDecimal3));
        System.out.println(bigDecimal1.multiply(bigDecimal3).compareTo(bigDecimal3));

    }

    public static void test1() {
        BigDecimal bigDecimal1 = new BigDecimal("0.1");
        BigDecimal bigDecimal2 = new BigDecimal("0.12");
        BigDecimal bigDecimal3 = new BigDecimal("0.3");
        BigDecimal bigDecimal7 = new BigDecimal("0.7");

        System.out.println(bigDecimal1.add(bigDecimal1));
        System.out.println(bigDecimal1.add(bigDecimal2));
        System.out.println(bigDecimal1.add(bigDecimal7));
        System.out.println(bigDecimal1.multiply(bigDecimal3));
        System.out.println(bigDecimal1.multiply(bigDecimal3).compareTo(bigDecimal3));

    }

}
