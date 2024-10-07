package org.bank.branch.attish.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class BootstrapUser implements CommandLineRunner {

    private final BankUserRepository bankUserRepository;

    public BootstrapUser(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    @Override
    public void run(String... args) {
        BankUser user1 = BankUser.builder()
                .firstName("John")
                .lastName("Doe")
                .birthdate("1985-04-12")
                .balance(1500.00)
                .username("jdoe")
                .transactionId(1234567890123456789L)
                .password("password1")
                .build();

        BankUser user2 = BankUser.builder()
                .firstName("Jane")
                .lastName("Smith")
                .birthdate("1990-08-24")
                .balance(2500.50)
                .username("jsmith")
                .transactionId(2345678901234567890L)
                .password("password2")
                .build();

        BankUser user3 = BankUser.builder()
                .firstName("Alice")
                .lastName("Johnson")
                .birthdate("1980-02-15")
                .balance(1800.75)
                .username("ajohnson")
                .transactionId(3456789012345678901L)
                .password("password3")
                .build();

        BankUser user4 = BankUser.builder()
                .firstName("Bob")
                .lastName("Brown")
                .birthdate("1975-12-05")
                .balance(3200.10)
                .username("bbrown")
                .transactionId(4567890123456789012L)
                .password("password4")
                .build();

        BankUser user5 = BankUser.builder()
                .firstName("Charlie")
                .lastName("Davis")
                .birthdate("2000-03-30")
                .balance(4100.00)
                .username("cdavis")
                .transactionId(5678901234567890123L)
                .password("password5")
                .build();


        log.info("BOOTSTRAPPING TEST USERS");

        bankUserRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));    }
}
