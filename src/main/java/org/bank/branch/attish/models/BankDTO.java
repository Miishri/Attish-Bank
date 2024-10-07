package org.bank.branch.attish.models;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public class BankDTO {
    private Long transactionId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private Timestamp creationDate;
}
