package com.github.gabrielalbernazdev.util;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtil {
    public static String getHttpRequestURIPath(HttpServletRequest httpRequest) {
        final String requestURI = httpRequest.getRequestURI();
        final String contextPath = httpRequest.getContextPath();
        return requestURI.substring(contextPath.length());
    }
}
