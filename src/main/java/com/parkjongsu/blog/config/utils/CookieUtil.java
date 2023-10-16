package com.parkjongsu.blog.config.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class CookieUtil {

    @Value("${spring.jwt.token-validity-in-seconds}")
    private long tokenValidityInSeconds;

    public Cookie createCookie(String cookieName, String value){
        Cookie token = new Cookie(cookieName,value);
//        token.setHttpOnly(true);
        token.setMaxAge((int)tokenValidityInSeconds);
        token.setPath("/");
        return token;
    }

    public Cookie getCookie(HttpServletRequest req, String cookieName){
        final Cookie[] cookies = req.getCookies();
        if(cookies==null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }

}