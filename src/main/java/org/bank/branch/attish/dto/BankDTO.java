package org.bank.branch.attish.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Builder
public class BankDTO {
    private Long transactionId;
    private String username;
    private String firstName;
    private String lastName;
    private String birthDate;
    private Timestamp creationDate;
}
