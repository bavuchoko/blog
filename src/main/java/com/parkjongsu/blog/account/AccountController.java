package com.parkjongsu.blog.account;


import com.parkjongsu.blog.account.dto.AccountDto;
import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.account.entity.AccountRole;
import com.parkjongsu.blog.account.service.AccountMapper;
import com.parkjongsu.blog.account.service.AccountService;
import com.parkjongsu.blog.common.annotation.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
@RestController
@RequestMapping(value = "/api/user",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/create")
    public ResponseEntity join(
            @RequestBody AccountDto accountDto,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        accountDto.setJoinDate(LocalDateTime.now());
        accountDto.setRoles(Set.of(AccountRole.USER));
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        AccountMapper.Instance.toDto(accountService.saveAccount(accountDto));
        String accessToken = accountService.authorize(accountDto, response, request);
        return ResponseEntity.ok().body(accessToken);
    }

    @GetMapping
    public ResponseEntity loadUserList(
            @CurrentUser Account account,
            Pageable pageable,
            PagedResourcesAssembler<Account> assembler

    ){
        Page<Account> page = accountService.loadUserList(pageable);
        CollectionModel pageResources = accountService.getPageResources(assembler, page, account);
        return ResponseEntity.ok(pageResources);
    }


    @PostMapping(value = "/reissue")
    public ResponseEntity refreshToekn(HttpServletRequest request) {
        String reissuedToken = accountService.reIssueToken(request);
        HttpHeaders headers = new HttpHeaders();
        headers.add("AUTHORIZATION", "BAERER "+reissuedToken);
        return new ResponseEntity(reissuedToken, headers, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse res){
        accountService.logout(req, res);
    }

}
