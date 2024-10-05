package org.bank.branch.attish.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.bank.branch.attish.security.auth.IUserAuthenticationFacade;
import org.bank.branch.attish.security.user.BankUserDetails;
import org.bank.branch.attish.services.BankUserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BankUserController {

    public static final String USER_PATH = "api/bank";
    public static final String USER_PATH_ID = USER_PATH + "{bankUserId}";

    private final BankUserService bankUserService;

    @GetMapping(USER_PATH_ID)
    public BankUser getBankUserById(@PathVariable UUID bankUserId) {
        return bankUserService.getById(bankUserId);
    }

    @GetMapping(USER_PATH + "/data")
    public BankUser getUserData() {
        return bankUserService.getData();
    }

    @PutMapping(USER_PATH + "/update/balance")
    public BankUser updateBankUserBalance(@RequestParam double balance) {
        return bankUserService.updateBalance(balance);
    }

    @PutMapping(USER_PATH + "/reset/password")
    public BankUser updateBankUserPassword(@RequestParam String password) {
        return bankUserService.updatePassword(password);
    }

    @PutMapping(USER_PATH + "/transfer")
    public Boolean transferBalance(@RequestParam double amount, @RequestParam Long toBankUserId) {
        return bankUserService.transfer(amount, toBankUserId);
    }

    @DeleteMapping(USER_PATH + "/delete")
    public Boolean deleteBankUser() {
        return bankUserService.delete();
    }

}
