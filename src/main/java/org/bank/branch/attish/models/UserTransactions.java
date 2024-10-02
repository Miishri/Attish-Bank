package org.bank.branch.attish.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.function.BinaryOperator;

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
}
