package org.bank.branch.attish.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BootstrapUser implements Runnable {

    private final BankUserRepository bankUserRepository;

    public BootstrapUser(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    @Override
    public void run() {
        BankUser testUser = BankUser.builder()
                .firstName("Test")
                .lastName("User")
                .birthdate("1990-01-01")
                .balance(1000.00)
                .username("user")
                .password("password")
                .build();

        log.info("BOOTSTRAPPING TEST USER {}", testUser);

        bankUserRepository.save(testUser);
    }
}
