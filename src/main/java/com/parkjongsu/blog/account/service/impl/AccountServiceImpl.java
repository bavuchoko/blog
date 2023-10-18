package com.parkjongsu.blog.account.service.impl;


import com.parkjongsu.blog.account.AccountController;
import com.parkjongsu.blog.account.dto.AccountAdapter;
import com.parkjongsu.blog.account.dto.AccountDto;
import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.account.repository.AccountJpaRepository;
import com.parkjongsu.blog.account.service.AccountMapper;
import com.parkjongsu.blog.account.service.AccountService;
import com.parkjongsu.blog.common.WebCommon;
import com.parkjongsu.blog.common.exception.AlreadyExistSuchDataCustomException;
import com.parkjongsu.blog.config.token.TokenManager;
import com.parkjongsu.blog.config.token.TokenType;
import com.parkjongsu.blog.config.utils.CookieUtil;
import com.parkjongsu.blog.config.utils.RedisUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {



    private final AccountJpaRepository accountJpaRepository;

    @Autowired
    CookieUtil cookieUtil;

    @Value("${spring.jwt.token-validity-one-min}")
    private long globalTimeOneMin;

    private final TokenManager tokenManager;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisUtil redisUtil;

    private final PasswordEncoder passwordEncoder;



    @Override
    public Account saveAccount(AccountDto accountDto) {

        accountJpaRepository.findByUsername(accountDto.getUsername()).ifPresent(e->{
            throw new AlreadyExistSuchDataCustomException("Duplicated username");
        });
        accountDto.setPassword(this.passwordEncoder.encode(accountDto.getPassword()));
        return this.accountJpaRepository.save(AccountMapper.Instance.toEntity(accountDto));
    }



    @Override
    public String authorize(AccountDto account, HttpServletResponse response, HttpServletRequest request) throws BadCredentialsException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenManager.createToken(authentication, TokenType.ACCESS_TOKEN);
        String refreshToken = tokenManager.createToken(authentication, TokenType.REFRESH_TOKEN);
        redisUtil.setData(refreshToken, WebCommon.getClientIp(request));

        Cookie refreshTokenCookie = cookieUtil.createCookie(TokenType.REFRESH_TOKEN.getValue(), refreshToken);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setHttpOnly(true);
        // expires in 7 days
        refreshTokenCookie.setMaxAge((int)(globalTimeOneMin * 60 * 24 * 14));
        response.addCookie(refreshTokenCookie);

        return accessToken;
    }

    @Override
    public String reIssueToken(HttpServletRequest request) {
        String storedRefreshToken = cookieUtil.getCookie(request, TokenType.REFRESH_TOKEN.getValue()).getValue();
        String clientIp = redisUtil.getData(storedRefreshToken);
        if(StringUtils.hasText(storedRefreshToken) && tokenManager.validateToken(storedRefreshToken)) {
            //refresh토큰으로 부터 인증객체 생성
            Authentication authentication = tokenManager.getAuthenticationFromRefreshToken(request);
            return tokenManager.createToken(authentication, TokenType.ACCESS_TOKEN);
        }else{
            throw new IllegalArgumentException("No valid refreshToken");
        }
    }

    @Override
    public void logout(HttpServletRequest req, HttpServletResponse res) {
        if(null != cookieUtil.getCookie(req, TokenType.REFRESH_TOKEN.getValue())){
            String refreshTokenInCookie = cookieUtil.getCookie(req, TokenType.REFRESH_TOKEN.getValue()).getValue();
            //todo 쿠키에 남은 토큰 삭제필요
            redisUtil.deleteData(refreshTokenInCookie);
            res.addCookie(cookieUtil.deleteCookie(req, TokenType.REFRESH_TOKEN.getValue()));
        }
    }

    @Override
    public Page<Account> loadUserList(Pageable pagable){
        return this.accountJpaRepository.findAll(pagable);
    }


    @Override
    public boolean validateToken(String token) {
        return tokenManager.validateToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AccountAdapter(accountJpaRepository.findByUsernameWithRoles(username)
                .orElseThrow(()->new UsernameNotFoundException(username)));
    }


    public CollectionModel getPageResources(
            PagedResourcesAssembler<Account> assembler,
            Page<Account> page,
            Account account
    ){
        return assembler.toModel(page, e -> {
            EntityModel<AccountDto> model = EntityModel.of(AccountMapper.Instance.toDto(e))
                    .add(linkTo(AccountController.class).slash(e.getId()).withSelfRel());
            if (account != null && account.equals(e)) {
                model.add(linkTo(AccountController.class).slash(e.getId()).withRel("update"));
            }
            return model;
        });
    }
}
