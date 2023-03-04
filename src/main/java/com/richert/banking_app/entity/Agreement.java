package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.AccountProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agreements")
public class Agreement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "status")
    @Enumerated(ORDINAL)
    private AccountProductStatus status;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "account_id",
            referencedColumnName = "id")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "product_id",
            referencedColumnName = "id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return createdAt == agreement.createdAt && account.equals(agreement.account) && product.equals(agreement.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, account, product);
    }
}