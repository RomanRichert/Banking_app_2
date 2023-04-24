package com.richert.banking_app.util;

import com.richert.banking_app.entity.*;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.UUID;

import static com.richert.banking_app.entity.enums.Currency.EUR;
import static com.richert.banking_app.entity.enums.Status.ACTIVE;
import static com.richert.banking_app.entity.enums.Status.PENDING;

@UtilityClass
public class EntityCreator {

    public static Client getClient() {
        Client client = new Client();

        client.setId(UUID.fromString("7823ddef-74de-4f06-96c4-2208456cc496"));
        client.setStatus(PENDING);
        client.setTaxCode("8254547854875");
        client.setFirstName("Test");
        client.setLastName("Client");
        client.setEmail("test@email.com");
        client.setAddress("Test address");
        client.setPhone("493783594879");
        client.setManager(getManager());

        return client;
    }

    public static Agreement getAgreement() {
        Agreement agreement = new Agreement();

        agreement.setId(1);
        agreement.setStatus(ACTIVE);
        agreement.setInterestRate(BigDecimal.valueOf(16.4444));
        agreement.setAccount(getAccount());
        agreement.setProduct(getProduct());

        return agreement;
    }

    public static Manager getManager() {
        Manager manager = new Manager();

        manager.setId(1);
        manager.setFirstName("Test");
        manager.setLastName("Manager");
        manager.setStatus(ACTIVE);

        return manager;
    }

    public static Account getAccount() {
        Account account = new Account();

        account.setId(UUID.fromString("7823ddef-74de-4f06-96c4-2a08456cc496"));
        account.setName(getClient().getFirstName() + " " + getClient().getLastName());
        account.setStatus(ACTIVE);
        account.setBalance(BigDecimal.valueOf(100));
        account.setCurrency(EUR);
        account.setClient(getClient());

        return account;
    }

    public static Product getProduct() {
        Product product = new Product();

        product.setId(14);
        product.setName("Test product");
        product.setStatus(ACTIVE);
        product.setCurrency(EUR);
        product.setInterestRate(BigDecimal.TEN);
        product.setLimit(1);
        product.setManager(getManager());

        return product;
    }
}