package org.bank.branch.attish.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_user")
@Setter
@Getter
@Data
public class BankUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotBlank(message = "Birthdate cannot be empty")
    private String birthdate;

    private Double balance;

    @Column(unique = true, nullable = false)
    private Long transactionId;

    @NotBlank(message = "Birthdate cannot be empty")
    private String username;

    @NotBlank(message = "Birthdate cannot be empty")
    private String password;

    @CreationTimestamp
    private Timestamp creationDate;
}
