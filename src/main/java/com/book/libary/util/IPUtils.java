package com.book.libary.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class IPUtils {
    public static String getCurrentIpAddress(final HttpServletRequest httpServletRequest) {
        final Optional<String> ipAddress = Optional.ofNullable(httpServletRequest.getHeader("X-Forwarded-For"));
        return ipAddress.orElse(httpServletRequest.getRemoteAddr());
    }
}
