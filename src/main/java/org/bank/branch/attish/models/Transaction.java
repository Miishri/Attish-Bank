package org.bank.branch.attish.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_user")
@Setter
@Getter
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromTransactionId;
    private Long toTransactionId;
    private Double amount;
    private boolean transferred;
}
