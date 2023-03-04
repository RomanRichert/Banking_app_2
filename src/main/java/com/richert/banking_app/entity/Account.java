package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.AccountStatus;
import com.richert.banking_app.entity.enums.AccountType;
import com.richert.banking_app.entity.enums.Currencies;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.ORDINAL;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.richert.banking_app.generator.UuidTimeSequenceGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private AccountType type;

    @Column(name = "status")
    @Enumerated(ORDINAL)
    private AccountStatus status;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    private Currencies currency;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "client_id",
            referencedColumnName = "id")
    private Client client;

    @OneToMany(cascade = ALL, mappedBy = "debitAccountId")
    private Set<Transaction> debitTransactions;

    @OneToMany(cascade = ALL, mappedBy = "creditAccountId")
    private Set<Transaction> creditTransactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && currency == account.currency && Objects.equals(client, account.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, client);
    }
}