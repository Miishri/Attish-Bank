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
public class BankUserController {

    public static final String USER_PATH = "api/bank/";
    public static final String USER_PATH_ID = USER_PATH + "{bankUserId}";

    private final BankUserService bankUserService;
    private final IUserAuthenticationFacade userAuthenticationFacade;

    @GetMapping(USER_PATH_ID)
    public BankUser getBankUserById(@PathVariable UUID bankUserId) {
        return bankUserService.getById(bankUserId);
    }

    @PutMapping(USER_PATH + "/balance")
    public BankUser updateBankUserBalance(@PathVariable UUID bankUserId, @RequestParam double balance) {
        return bankUserService.updateBalance(bankUserId, balance);
    }

    @PutMapping(USER_PATH + "/password/{bankUserId}")
    public BankUser updateBankUserPassword(@PathVariable UUID bankUserId, @RequestParam String password) {
        return bankUserService.updatePassword(bankUserId, password);
    }

    @DeleteMapping(USER_PATH)
    public boolean deleteBankUser() {
        String bankUsername = userAuthenticationFacade.getUsername();
        return bankUserService.delete(bankUsername);
    }

}
