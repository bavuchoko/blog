package com.parkjongsu.blog.config.interceptor;

import com.parkjongsu.blog.config.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.time.LocalTime;

@Slf4j
@RequiredArgsConstructor
public class VisitorCounterInterceptor implements HandlerInterceptor {

    private final CookieUtil cookieUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userAgent = request.getHeader("User-Agent");
        if(userAgent != null && userAgent.contains("Mozilla")){
            LocalTime currentTime = LocalTime.now();
            LocalTime midnight = LocalTime.of(23,59,59);
            Duration timeUntilMidnight = Duration.between(currentTime, midnight);
            long secondsUntilMidnight = timeUntilMidnight.getSeconds();

            Cookie lastPageViewTimeCookie = cookieUtil.getCookie(request,"lastPageViewTime");
            if(lastPageViewTimeCookie != null){
                lastPageViewTimeCookie.setValue(String.valueOf(currentTime.toSecondOfDay()));
            }else{
                lastPageViewTimeCookie = new Cookie("lastPageViewTime", String.valueOf(currentTime.toSecondOfDay()));
            }
            lastPageViewTimeCookie.setMaxAge((int) secondsUntilMidnight);
            response.addCookie(lastPageViewTimeCookie);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, null);
//    }
}
