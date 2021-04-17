package edu.sgu.seminar.utils;

import java.util.Random;

public class RandomUtils {
    public static String randomCode(Integer min,Integer max){
        Random random= new Random();
        Integer code=random.nextInt((max - min) + 1) + min;
        return code.toString();
    }
}
