package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.FetchType.LAZY;

@Setter
@Getter
@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.richert.banking_app.generator.UuidTimeSequenceGenerator")
    private String id;

    @Enumerated(ORDINAL)
    @Column(name = "type")
    private TransactionType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "debit_account_id",
            referencedColumnName = "id")
    private Account debitAccount;

    @ManyToOne(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "credit_account_id",
            referencedColumnName = "id")
    private Account creditAccount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return createdAt == that.createdAt && Objects.equals(debitAccount, that.debitAccount) && Objects.equals(creditAccount, that.creditAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, debitAccount, creditAccount, id);
    }

    public Transaction(TransactionType type, BigDecimal amount, String description, Account debitAccount, Account creditAccount) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
    }
}