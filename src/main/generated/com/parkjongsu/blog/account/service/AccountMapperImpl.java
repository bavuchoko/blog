package com.parkjongsu.blog.account.service;

import com.parkjongsu.blog.account.dto.AccountDto;
import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.account.entity.AccountRole;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-11T22:22:38+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.id( accountDto.getId() );
        account.username( accountDto.getUsername() );
        account.birth( accountDto.getBirth() );
        account.password( accountDto.getPassword() );
        account.name( accountDto.getName() );
        account.portrait( accountDto.getPortrait() );
        account.joinDate( accountDto.getJoinDate() );
        Set<AccountRole> set = accountDto.getRoles();
        if ( set != null ) {
            account.roles( new LinkedHashSet<AccountRole>( set ) );
        }
        account.token( accountDto.getToken() );

        return account.build();
    }

    @Override
    public AccountDto toDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto.AccountDtoBuilder accountDto = AccountDto.builder();

        accountDto.id( account.getId() );
        accountDto.username( account.getUsername() );
        accountDto.birth( account.getBirth() );
        accountDto.name( account.getName() );
        accountDto.portrait( account.getPortrait() );
        Set<AccountRole> set = account.getRoles();
        if ( set != null ) {
            accountDto.roles( new LinkedHashSet<AccountRole>( set ) );
        }
        accountDto.token( account.getToken() );
        accountDto.joinDate( account.getJoinDate() );

        return accountDto.build();
    }

    @Override
    public Account withoutRoles(Account account) {
        if ( account == null ) {
            return null;
        }

        Account.AccountBuilder account1 = Account.builder();

        account1.id( account.getId() );
        account1.username( account.getUsername() );
        account1.birth( account.getBirth() );
        account1.name( account.getName() );
        account1.portrait( account.getPortrait() );
        account1.joinDate( account.getJoinDate() );
        account1.token( account.getToken() );

        return account1.build();
    }

    @Override
    public Account entiTyToEntity(Account account) {
        if ( account == null ) {
            return null;
        }

        Account.AccountBuilder account1 = Account.builder();

        account1.id( account.getId() );
        account1.username( account.getUsername() );
        account1.birth( account.getBirth() );
        account1.password( account.getPassword() );
        account1.name( account.getName() );
        account1.portrait( account.getPortrait() );
        account1.joinDate( account.getJoinDate() );
        Set<AccountRole> set = account.getRoles();
        if ( set != null ) {
            account1.roles( new LinkedHashSet<AccountRole>( set ) );
        }
        account1.token( account.getToken() );

        return account1.build();
    }
}
