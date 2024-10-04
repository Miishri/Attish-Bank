package org.bank.branch.attish.services;

import org.bank.branch.attish.models.BankUser;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface BankUserService {

    BankUser getById(UUID bankUserId);
    BankUser updateBalance(double balance);
    BankUser updatePassword(String newPassword);
    boolean delete();
    Boolean transfer(double amount, Long toBankUserId);
}
