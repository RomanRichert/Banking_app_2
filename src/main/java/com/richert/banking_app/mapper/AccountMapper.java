package com.richert.banking_app.mapper;

import com.richert.banking_app.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    static String getId(Account account) {
        return account.getId();
    }
}