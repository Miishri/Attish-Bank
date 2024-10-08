package org.bank.branch.attish.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.dto.BankDTO;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.models.UserTransaction;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.bank.branch.attish.respositories.UserTransactionRepository;
import org.bank.branch.attish.security.auth.IUserAuthenticationFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankUserServiceImpl implements BankUserService {

    private final BankUserRepository bankUserRepository;
    private final UserTransactionRepository userTransactionRepository;
    private final IUserAuthenticationFacade userAuthenticationFacade;

    @Override
    public BankUser getById(UUID bankUserId) {
        return bankUserRepository.findById(bankUserId).orElseThrow();
    }

    @Override
    public List<BankDTO> getUsers() {
        return bankUserRepository.findAll().stream()
                .map(bankUser -> BankDTO.builder()
                        .transactionId(bankUser.getTransactionId())
                        .username(bankUser.getUsername())
                        .firstName(bankUser.getFirstName())
                        .lastName(bankUser.getLastName())
                        .birthDate(bankUser.getBirthdate())
                        .creationDate(bankUser.getCreationDate())
                        .build())
                .toList();
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


            if (hasBalance(fromUser, amount)) {
                log.error("IN BankUserService - TRANSACTION COMPLETED");

                fromUser.setBalance(fromUser.getBalance() - amount);
                toUser.setBalance(toUser.getBalance() + amount);

                bankUserRepository.save(toUser);
                bankUserRepository.save(fromUser);
                return true;
            }
        }

        log.error("IN BankUserService - TRANSACTION FAILED");
        return false;
    }

    @Override
    public BankUser getData() {
        return getAuthenticatedUser();
    }

    private BankUser getAuthenticatedUser() {
        String bankUsername = userAuthenticationFacade.getUsername();
        return bankUserRepository.findBankUserByUsername(bankUsername);
    }

    private boolean hasBalance(BankUser bankUser, double transferAmount) {
        return bankUser.getBalance() - transferAmount >= 0;
    }
}
