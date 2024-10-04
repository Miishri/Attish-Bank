package org.bank.branch.attish.services;

import org.bank.branch.attish.models.BankUser;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface BankUserService {

    BankUser getById(UUID bankUserId);
    BankUser updateBalance(UUID bankUserId, double balance);
    BankUser updatePassword(UUID bankUserId, String newPassword);
    boolean delete(String username);
}
