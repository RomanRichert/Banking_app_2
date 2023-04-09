package com.richert.banking_app.repository;

import com.richert.banking_app.entity.Client;
import com.richert.banking_app.entity.enums.AccountStatus;
import com.richert.banking_app.entity.enums.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Transactional
    @Modifying
    @Query("update Client c set c.status = ?1 where c.id = ?2")
    void updateStatusById(@NonNull ClientStatus status, String id);
    List<Client> findByStatusNot(ClientStatus status);

    @Query("""
            select c from Client c inner join c.accounts accounts
            where accounts.balance > ?1 and accounts.status <> ?2 and c.status <> ?3""")
    List<Client> findByAccountsBalanceGreaterThan(BigDecimal balance, AccountStatus status, ClientStatus status1);

    @Transactional
    @Modifying
    @Query("""
            update Client c set c.firstName = ?1, c.lastName = ?2, c.email = ?3, c.address = ?4, c.phone = ?5, c.updatedAt = ?6
            where c.id = ?7""")
    void updateClientById(String firstName, String lastName, String email, String address, String phone, Timestamp updatedAt, UUID id);

    List<Client> findByStatus(ClientStatus status);
}