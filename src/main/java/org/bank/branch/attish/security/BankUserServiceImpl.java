package org.bank.branch.attish.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankUserServiceImpl implements BankUserService {

    private final BankUserRepository bankUserRepository;

    @Override
    public BankUser getBankUserById(UUID bankUserId) {
        return bankUserRepository.findById(bankUserId).orElseThrow();
    }

    @Override
    public boolean registerBankUser(BankUser bankUser) {
        if (usernameExists(bankUser.getUsername())) return false;

        bankUserRepository.save(bankUser);
        return true;
    }

    @Override
    public BankUser updateBankUserBalance(UUID bankUserId, double balance) {
        BankUser bankUserPlaceHolder = bankUserRepository.findById(bankUserId).orElseThrow();
        bankUserPlaceHolder.setBalance(balance);

        return bankUserRepository.save(bankUserPlaceHolder);
    }

    @Override
    public BankUser updateBankUserPassword(UUID bankUserId, String newPassword) {

        BankUser bankUserPlaceHolder = bankUserRepository.findById(bankUserId).orElseThrow();
        bankUserPlaceHolder.setPassword(newPassword);
        return bankUserRepository.save(bankUserPlaceHolder);
    }

    @Override
    public boolean deleteBankUser(UUID bankUserId) {
        if (bankUserRepository.existsById(bankUserId)) {
            bankUserRepository.deleteById(bankUserId);
            return true;
        }

        return false;
    }

    private boolean usernameExists(String username) {
        return bankUserRepository.findAll().stream().anyMatch(u -> u.getUsername().equals(username));
    }
}
