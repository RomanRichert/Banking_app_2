package com.richert.banking_app.repository;

import com.richert.banking_app.entity.Client;
import com.richert.banking_app.entity.enums.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByAccountsBalanceGreaterThan(@NonNull BigDecimal balance);

    List<Client> findByStatus(ClientStatus status);
}