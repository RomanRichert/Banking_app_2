package com.richert.banking_app.entity;

import com.richert.banking_app.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

import static jakarta.persistence.EnumType.ORDINAL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "managers")
public class Manager {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "First name shouldn't be empty")
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "First name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 characters")
    private String lastName;

    @Enumerated(ORDINAL)
    @Column(name = "status")
    private Status status;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

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