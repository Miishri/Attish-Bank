package org.bank.branch.attish.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankUserServiceImpl implements BankUserService {

    private final BankUserRepository bankUserRepository;

    @Override
    public BankUser getById(UUID bankUserId) {
        return bankUserRepository.findById(bankUserId).orElseThrow();
    }

    @Override
    public BankUser updateBalance(UUID bankUserId, double balance) {
        BankUser bankUserPlaceHolder = bankUserRepository.findById(bankUserId).orElseThrow();
        bankUserPlaceHolder.setBalance(balance);

        return bankUserRepository.save(bankUserPlaceHolder);
    }

    @Override
    public BankUser updatePassword(UUID bankUserId, String newPassword) {
        BankUser bankUserPlaceHolder = bankUserRepository.findById(bankUserId).orElseThrow();
        bankUserPlaceHolder.setPassword(newPassword);
        return bankUserRepository.save(bankUserPlaceHolder);
    }

    @Override
    public boolean delete(String bankUsername) {
        return bankUserRepository.deleteBankUserByUsername(bankUsername);
    }
}
