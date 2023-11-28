package com.book.libary.util;

public class RandomStringGenerate {
    public static String randomString(){
        return org.apache.commons.lang3.RandomStringUtils.random(10, true, true);
    }
}
