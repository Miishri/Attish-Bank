package org.bank.branch.attish.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_user")
@Setter
@Getter
@Data
public class UserTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false)
    private UUID id;

    private Long fromTransactionId;
    private Long toTransactionId;
    private Double amount;
    private Boolean transferred;
}
