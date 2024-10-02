package org.bank.branch.attish.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Setter
@Getter
public class BankUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String birthdate;
    private Double balance;

    private String username;
    private String password;

    @CreationTimestamp
    private Timestamp creationDate;
    private boolean tokenExpired;

}
