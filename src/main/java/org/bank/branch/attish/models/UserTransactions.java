package org.bank.branch.attish.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID fromUser;
    private UUID toUser;
    private Double amount;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "bank_user_id")
    private BankUser bankUser;
}
