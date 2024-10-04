package org.bank.branch.attish.respositories;

import org.bank.branch.attish.models.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, UUID> {
    Boolean existsBankUserByUsername(String username);
    BankUser findBankUserByUsername(String username);
    Boolean deleteBankUserByUsername(String username);
    Boolean existsBankUserByTransactionId(Long transactionId);
    BankUser findBankUserByTransactionId(Long transactionId);
}
