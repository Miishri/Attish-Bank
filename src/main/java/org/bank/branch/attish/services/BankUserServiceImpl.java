package org.bank.branch.attish.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.bank.branch.attish.security.auth.IUserAuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankUserServiceImpl implements BankUserService {

    private final BankUserRepository bankUserRepository;
    private final IUserAuthenticationFacade userAuthenticationFacade;

    @Override
    public BankUser getById(UUID bankUserId) {
        return bankUserRepository.findById(bankUserId).orElseThrow();
    }

    @Override
    public BankUser updateBalance(double balance) {
        BankUser updatedUser = getAuthenticatedUser();
        updatedUser.setBalance(balance);
        return bankUserRepository.save(updatedUser);
    }

    @Override
    public BankUser updatePassword(String newPassword) {
        BankUser updatedUser = getAuthenticatedUser();
        updatedUser.setPassword(newPassword);
        return bankUserRepository.save(updatedUser);
    }

    @Override
    public boolean delete() {
        System.out.println("ACCOUNT DELETED");
        if (getAuthenticatedUser().getId() == null) {
            return false;
        }
        bankUserRepository.deleteById(getAuthenticatedUser().getId());
        return true;
    }

    @Override
    public Boolean transfer(double amount, Long toBankUserId) {
        if (bankUserRepository.existsBankUserByTransactionId(toBankUserId) && !Objects.equals(toBankUserId, getAuthenticatedUser().getTransactionId())) {
            BankUser fromUser = getAuthenticatedUser();
            BankUser toUser = bankUserRepository.findBankUserByTransactionId(toBankUserId);

            System.out.println("TRANSFERING FROM: " + fromUser);

            if (hasBalance(fromUser, amount)) {
                System.out.println("TRANSFERED");


                fromUser.setBalance(fromUser.getBalance() - amount);
                toUser.setBalance(toUser.getBalance() + amount);

                bankUserRepository.save(toUser);
                bankUserRepository.save(fromUser);
                return true;
            }
        }

        return false;
    }

    @Override
    public BankUser getData() {
        System.out.println("TRIED TO FETCH DATA");
        return getAuthenticatedUser();
    }

    private BankUser getAuthenticatedUser() {
        String bankUsername = userAuthenticationFacade.getUsername();
        System.out.println(bankUsername);
        return bankUserRepository.findBankUserByUsername(bankUsername);
    }

    private boolean hasBalance(BankUser bankUser, double transferAmount) {
        return bankUser.getBalance() - transferAmount >= 0;
    }
}
