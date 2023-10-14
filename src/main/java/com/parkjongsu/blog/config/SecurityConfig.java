package com.parkjongsu.blog.config;

import com.parkjongsu.blog.config.filter.TokenAccessDeniedHandler;
import com.parkjongsu.blog.config.filter.TokenAuthenticationEntryPoint;
import com.parkjongsu.blog.config.token.TokenManager;
import com.parkjongsu.blog.config.token.TokenSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final TokenManager tokenManager;
    private final TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;


    public SecurityConfig(TokenManager tokenManager, TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint, TokenAccessDeniedHandler tokenAccessDeniedHandler) {
        this.tokenManager = tokenManager;
        this.tokenAuthenticationEntryPoint = tokenAuthenticationEntryPoint;
        this.tokenAccessDeniedHandler = tokenAccessDeniedHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(configuer ->
                        configuer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/api/admin").hasRole("ADMIN")
                )
                .exceptionHandling(authenticationManager ->
                        authenticationManager
                        .authenticationEntryPoint(tokenAuthenticationEntryPoint)
                        .accessDeniedHandler(tokenAccessDeniedHandler)
                )
                .apply(new TokenSecurityConfig(tokenManager));

         return httpSecurity.build();
    }

}
