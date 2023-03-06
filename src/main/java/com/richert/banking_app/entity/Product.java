package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.Currencies;
import com.richert.banking_app.entity.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.lang.System.currentTimeMillis;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @NotBlank(message = "Product name shouldn't be empty")
    @Size(min = 1, max = 70, message = "Product name should be between 1 and 70 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(ORDINAL)
    private ProductStatus status;

    @Enumerated(ORDINAL)
    @Column(name = "currency_code")
    private Currencies currency;

    @Column(name = "interest_rate")
    private BigDecimal interestRate = BigDecimal.ZERO;

    @Column(name = "limit")
    private int limit;

    @Column(name = "created_at")
    private final Timestamp createdAt = new Timestamp(currentTimeMillis());

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

    public Product(String name, ProductStatus status, Currencies currency, int limit) {
        this.name = name;
        this.status = status;
        this.currency = currency;
        this.limit = limit;
    }
}