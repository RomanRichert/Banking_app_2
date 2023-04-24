package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.Status;
import com.richert.banking_app.entity.enums.Currency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.richert.banking_app.generator.UuidTimeSequenceGenerator")
    private UUID id;

    @NotBlank(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(ORDINAL)
    private Status status;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency_code")
    private Currency currency;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "client_id",
            referencedColumnName = "id")
    private Client client;

    @OneToMany(cascade = {PERSIST, MERGE, REFRESH}, mappedBy = "account", fetch = LAZY)
    private Set<Agreement> agreements = new LinkedHashSet<>();

    @OneToMany(cascade = {PERSIST, MERGE, REFRESH}, mappedBy = "debitAccount", fetch = LAZY)
    private Set<Transaction> debitTransactions = new LinkedHashSet<>();

    @OneToMany(cascade = {PERSIST, MERGE, REFRESH}, mappedBy = "creditAccount", fetch = LAZY)
    private Set<Transaction> creditTransactions = new LinkedHashSet<>();

    public void addAgreement(Agreement agreement){
        agreements.add(agreement);
    }

    public void addDebitTransaction(Transaction transaction){
        debitTransactions.add(transaction);
    }

    public void addCreditTransaction(Transaction transaction){
        creditTransactions.add(transaction);
    }

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