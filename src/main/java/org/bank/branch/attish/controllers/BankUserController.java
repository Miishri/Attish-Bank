package org.bank.branch.attish.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.security.BankUserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BankUserController {

    public static final String USER_PATH = "/bank-user";
    public static final String USER_PATH_ID = "/bank-user/{bankUserId}";

    private final BankUserService bankUserService;

    @GetMapping(USER_PATH_ID)
    public BankUser getBankUserById(@PathVariable("bankUserId") UUID bankUserId) {
        return bankUserService.getById(bankUserId);
    }

    @PostMapping(USER_PATH)
    public boolean createBankUser(@RequestBody BankUser bankUser) {
        return bankUserService.register(bankUser);
    }

    @PutMapping(USER_PATH_ID)
    public BankUser updateBankUserBalance(@PathVariable UUID bankUserId, @RequestParam double balance) {
        return bankUserService.updateBalance(bankUserId, balance);
    }

    @PutMapping(USER_PATH_ID)
    public BankUser updateBankUserPassword(@PathVariable UUID bankUserId, @RequestParam String password) {
        return bankUserService.updatePassword(bankUserId, password);
    }

    @DeleteMapping(USER_PATH_ID)
    public boolean deleteBankUser(@PathVariable UUID bankUserId) {
        return bankUserService.delete(bankUserId);
    }

}
