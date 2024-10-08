package org.bank.branch.attish.services;

import org.bank.branch.attish.dto.BankDTO;
import org.bank.branch.attish.models.BankUser;

import java.util.List;
import java.util.UUID;

public interface BankUserService {

    BankUser getById(UUID bankUserId);
    List<BankDTO> getUsers();
    BankUser updateBalance(double balance);
    BankUser updatePassword(String newPassword);
    boolean delete();
    Boolean transfer(double amount, Long toBankUserId);
    BankUser getData();

}
