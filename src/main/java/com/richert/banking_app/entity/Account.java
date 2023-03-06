package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.AccountStatus;
import com.richert.banking_app.entity.enums.AccountType;
import com.richert.banking_app.entity.enums.Currencies;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.ORDINAL;
import static java.lang.System.currentTimeMillis;

@Getter
@Setter
@Entity
@Table(name = "accounts")
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.richert.banking_app.generator.UuidTimeSequenceGenerator")
    private String id;

    @NotBlank(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private AccountType type;

    @Column(name = "status")
    @Enumerated(ORDINAL)
    private AccountStatus status;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.valueOf(50);

    @Column(name = "currency_code")
    private Currencies currency;

    @Column(name = "created_at")
    private final Timestamp createdAt = new Timestamp(currentTimeMillis());

    @Column(name = "updated_at")
    private Timestamp updatedAt = createdAt;

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

    public Account(String name, AccountType type, AccountStatus status, BigDecimal balance, Currencies currency, Client client) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.balance = balance;
        this.currency = currency;
        this.client = client;
    }
}