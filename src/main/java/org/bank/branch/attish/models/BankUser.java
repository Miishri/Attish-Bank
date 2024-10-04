package org.bank.branch.attish.models;

import jakarta.persistence.*;
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

    private String firstName;
    private String lastName;
    private String birthdate;
    private Double balance;

    @Column(unique = true, nullable = false)
    private Long transactionId;

    private String username;
    private String password;

    @CreationTimestamp
    private Timestamp creationDate;
}
