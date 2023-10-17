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
        //웹접근이면
        if(userAgent != null && userAgent.contains("Mozilla")){
            LocalTime currentTime = LocalTime.now();
            LocalTime midnight = LocalTime.of(23,59,59);
            Duration timeUntilMidnight = Duration.between(currentTime, midnight);
            long secondsUntilMidnight = timeUntilMidnight.getSeconds();
            Cookie lastPageViewTimeCookie = cookieUtil.getCookie(request,"lastPageViewTime");
            //쿠키가 있으면
            if(lastPageViewTimeCookie != null){
                //시간이 30분 이상 지났으면
                int lastPageViewTime = Integer.valueOf(lastPageViewTimeCookie.getValue());
                if(currentTime.toSecondOfDay() - lastPageViewTime > 1800) {
                    //방문자 증가
                    this.increaseVisitCount();
                };
                //lastPageViewTime 업데이트
                lastPageViewTimeCookie.setValue(String.valueOf(currentTime.toSecondOfDay()));

            }else{
            //쿠키가 없으면
                //방문자 증가
                this.increaseVisitCount();
                //lastPageViewTime 업데이트
                lastPageViewTimeCookie = new Cookie("lastPageViewTime", String.valueOf(currentTime.toSecondOfDay()));
            }

            //23시 59분 59초를 쿠키 유효시간으로
            lastPageViewTimeCookie.setMaxAge((int) secondsUntilMidnight);
            response.addCookie(lastPageViewTimeCookie);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    public void increaseVisitCount() throws Exception {
        log.info("방문자 수 증가");
    }
}
