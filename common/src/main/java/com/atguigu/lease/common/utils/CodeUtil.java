package com.atguigu.lease.common.utils;

import java.util.Random;

public class CodeUtil {


    public static String getRamdomCode(Integer length){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }
}
