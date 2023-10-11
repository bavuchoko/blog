package com.parkjongsu.blog.account.service;


import com.parkjongsu.blog.account.dto.AccountDto;
import com.parkjongsu.blog.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper Instance = Mappers.getMapper(AccountMapper.class);
    @Named("toEntity")
    Account toEntity(AccountDto accountDto);

    @Mapping(target = "password", ignore = true)
    @Named("toDto")
    AccountDto toDto(Account account);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Named("withoutRoles")
    Account withoutRoles(Account account);

    @Named("entiTyToEntity")
    Account entiTyToEntity(Account account);




}
