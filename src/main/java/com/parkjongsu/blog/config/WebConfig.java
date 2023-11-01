package com.parkjongsu.blog.config;

import com.parkjongsu.blog.config.interceptor.VisitorCounterInterceptor;
import com.parkjongsu.blog.config.utils.CookieUtil;
import com.parkjongsu.blog.config.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;

    @Value("${files.imagePath}")
    private String IMAGE_PATH;
    @Value("${files.filePath}")
    private String FILE_PATH;
    @Value("${files.prefix}")
    private String PATH_PREFIX;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitorCounterInterceptor(cookieUtil, redisUtil))
                .excludePathPatterns("/css/**", "/upload/**", "/js/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///"+ PATH_PREFIX + IMAGE_PATH);
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:///"+ PATH_PREFIX + FILE_PATH);
    }

}
