package com.parkjongsu.blog.account.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.parkjongsu.blog.account.entity.AccountRole;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {


    private Integer id;
    @NotBlank(message = "아이디는 필수값입니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;
    private String name;
    private String portrait;
    private LocalDateTime joinDate;
    private Set<AccountRole> roles;

}

