package com.parkjongsu.blog.config;

import com.parkjongsu.blog.config.interceptor.VisitorCounterInterceptor;
import com.parkjongsu.blog.config.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CookieUtil cookieUtil;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitorCounterInterceptor(cookieUtil))
                .excludePathPatterns("/css/**", "/images/**", "/js/**");
    }
}
