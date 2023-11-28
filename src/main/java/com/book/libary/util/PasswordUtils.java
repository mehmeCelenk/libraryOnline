package com.book.libary.util;

import org.apache.commons.codec.digest.DigestUtils;
public class PasswordUtils {
    public static String encode(final String password){
        return DigestUtils.sha256Hex(password);
    }
}
