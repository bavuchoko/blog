package com.parkjongsu.blog.config.token;

import com.parkjongsu.blog.account.dto.AccountDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface TokenManager {

    String createToken(Authentication authentication, TokenType tokenType);
    Authentication getAuthenticationFromRefreshToken(HttpServletRequest request);
    boolean validateToken(String token);

    Authentication getAuthentication(String token);
}