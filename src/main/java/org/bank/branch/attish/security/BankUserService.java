package org.bank.branch.attish.security;

import org.bank.branch.attish.models.BankUser;

import java.util.UUID;

public interface BankUserService {

    BankUser getBankUserById(UUID bankUserId);
    BankUser registerBankUser(BankUser bankUser);
    BankUser updateBankUser(UUID bankUserId, BankUser bankUser);
    boolean deleteBankUser(UUID bankUserId);
}
