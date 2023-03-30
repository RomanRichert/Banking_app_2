package com.richert.banking_app.util;

import com.richert.banking_app.entity.*;
import com.richert.banking_app.entity.enums.*;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class EntityCreator {

    public static Client getClient(){
        return new Client(
                ClientStatus.PENDING,
                "8254547854875",
                "Aleksey",
                "Lavrov",
                "kentaskis.l@gmail.com",
                "Kamen, Einsteinstrasse 3",
                "380668882744",
                getManager()
        );
    }

    public static Agreement getAgreement() {
        return new Agreement(
                AccountProductStatus.ACTIVE,
                BigDecimal.valueOf(16.4444),
                getAccount(),
                getProduct()
        );
    }

    public static Manager getManager() {
        return new Manager(
                "Aleksey",
                "Pobedonosec",
                ManagerStatus.PENDING
        );
    }

    public static Account getAccount() {
        return new Account(
                "Account1",
                AccountType.NEW,
                AccountStatus.NEW,
                Currency.EUR,
                getClient()
        );
    }

    public static Product getProduct() {
        return new Product(
                "Credit",
                ProductStatus.NEW,
                Currency.EUR,
                10
        );
    }
}