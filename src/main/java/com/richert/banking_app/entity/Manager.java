package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.ManagerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "managers")
public class Manager {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    @Enumerated(ORDINAL)
    private ManagerStatus status;

    @Column(name = "created_at")
    public Timestamp createdAt;

    @Column(name = "updated_at")
    public Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return createdAt == manager.createdAt && firstName.equals(manager.firstName) && lastName.equals(manager.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, createdAt, id);
    }
}