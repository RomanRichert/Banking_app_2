package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.Currencies;
import com.richert.banking_app.entity.enums.ProductStatus;
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
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(ORDINAL)
    private ProductStatus status;

    @Enumerated(ORDINAL)
    @Column(name = "currency_code")
    private Currencies currency;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "limit")
    private int limit;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne()
    @JoinColumn(name = "manager_id",
            referencedColumnName = "id")
    private Manager manager;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return createdAt == product.createdAt && Objects.equals(name, product.name) && currency == product.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manager, createdAt);
    }
}