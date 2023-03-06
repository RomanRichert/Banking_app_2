package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.ClientStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.ORDINAL;
import static java.lang.System.currentTimeMillis;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.richert.banking_app.generator.UuidTimeSequenceGenerator")
    private String id;

    @Column(name = "status")
    @Enumerated(ORDINAL)
    private ClientStatus status;

    @NotBlank(message = "Tax code shouldn't be empty")
    @Size(min = 1, max = 20, message = "Tax code should be between 1 and 20 characters")
    @Column(name = "tax_code")
    private String taxCode;

    @NotBlank(message = "First name shouldn't be empty")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    @Column(name = "last_name")
    private String lastName;


    @Email(message = "Invalid email")
    @NotBlank(message = "Email shouldn't be empty")
    @Size(max = 60, message = "Max size of the email is 60 characters")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Address shouldn't be empty")
    @Size(min = 10, max = 80, message = "Address should be between 10 and 80 characters")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Phone number shouldn't be empty")
    @Size(min = 1, max = 20, message = "Phone number should be between 1 and 20 characters")
    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private final Timestamp createdAt = new Timestamp(currentTimeMillis());

    @Column(name = "updated_at")
    private Timestamp updatedAt = createdAt;

    @ManyToOne()
    @JoinColumn(name = "manager_id",
            referencedColumnName = "id")
    private Manager manager;

    @OneToMany(cascade = ALL, mappedBy = "client")
    private Set<Account> accounts = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return taxCode.equals(client.taxCode) && email.equals(client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    public Set<String> getAccounts() {
        Set<String> set = new LinkedHashSet<>();

        for (Account a : accounts) {
            set.add(a.toString());
        }

        return set;
    }

    public String getManager() {
        return manager.toString();
    }

    public Client(ClientStatus status, String taxCode, String firstName, String lastName, String email, String address, String phone, Manager manager) {
        this.status = status;
        this.taxCode = taxCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.manager = manager;
    }
}