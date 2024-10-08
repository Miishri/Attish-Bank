package org.bank.branch.attish.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class TransactionDTO {
    
    private Long transactionId;
    private Long toTransactionId;
    private Double amount;
    private boolean transferred;
}
