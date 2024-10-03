package org.bank.branch.attish.security;

import org.bank.branch.attish.models.BankUser;

import java.util.UUID;

public interface BankUserService {

    BankUser getById(UUID bankUserId);
    boolean register(BankUser bankUser);
    BankUser updateBalance(UUID bankUserId, double balance);
    BankUser updatePassword(UUID bankUserId, String newPassword);
    boolean delete(UUID bankUserId);
}
