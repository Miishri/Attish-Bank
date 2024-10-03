package org.bank.branch.attish.security;

import org.bank.branch.attish.models.BankUser;

import java.util.UUID;

public interface BankUserService {

    BankUser getBankUserById(UUID bankUserId);
    boolean registerBankUser(BankUser bankUser);
    BankUser updateBankUserBalance(UUID bankUserId, double balance);
    BankUser updateBankUserPassword(UUID bankUserId, String newPassword);
    boolean deleteBankUser(UUID bankUserId);
}
