package com.parkjongsu.blog.account.service;

import com.parkjongsu.blog.account.dto.AccountDto;
import com.parkjongsu.blog.account.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface AccountService extends UserDetailsService {
    Account saveAccount(AccountDto accountDto);

    String authorize(AccountDto accountDto, HttpServletResponse response, HttpServletRequest request);

    String reIssueToken(HttpServletRequest request);

    void logout(HttpServletRequest req);

    Page<Account> loadUserList(Pageable pagable);

    boolean validateToken(String token);
    CollectionModel getPageResources(PagedResourcesAssembler<Account> assembler, Page<Account> page, Account account);

}
