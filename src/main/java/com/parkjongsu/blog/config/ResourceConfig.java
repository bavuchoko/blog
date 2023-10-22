package com.parkjongsu.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ResourceConfig implements WebMvcConfigurer {

    @Value("${file.downloadPath}")
    private String FILE_PATH;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/upload/**")
                .addResourceLocations(FILE_PATH);
    }
}
