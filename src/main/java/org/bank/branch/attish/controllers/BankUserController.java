package org.bank.branch.attish.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankDTO;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.bank.branch.attish.security.auth.IUserAuthenticationFacade;
import org.bank.branch.attish.security.user.BankUserDetails;
import org.bank.branch.attish.services.BankUserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"https://attish-bank-site.vercel.app/", "http://localhost:5173"})
public class BankUserController {

    public static final String USER_PATH = "api/bank";
    public static final String USER_PATH_ID = USER_PATH + "{bankUserId}";

    private final BankUserService bankUserService;

    @GetMapping(USER_PATH_ID )
    public BankUser getBankUserById(@PathVariable UUID bankUserId) {
        log.info("IN BankUserController - User Queried by Id {}", bankUserId);

        return bankUserService.getById(bankUserId);
    }

    @GetMapping(USER_PATH + "/users")
    public List<BankDTO> getBankUsers() {
        log.info("IN BankUserController - All Bank Users Fetched");

        return bankUserService.getUsers();
    }

    @GetMapping(USER_PATH + "/data")
    public BankUser getUserData() {
        log.info("IN BankUserController - User Data Queried");

        return bankUserService.getData();
    }

    @PutMapping(USER_PATH + "/update/balance")
    public BankUser updateBankUserBalance(@RequestParam double balance) {
        log.info("IN BankUserController - Active User Balance Updated {}", balance);

        return bankUserService.updateBalance(balance);
    }

    @PutMapping(USER_PATH + "/reset/password")
    public BankUser updateBankUserPassword(@RequestParam String password) {
        log.info("IN BankUserController - Active User Password Updated {}", password);

        return bankUserService.updatePassword(password);
    }

    @PutMapping(USER_PATH + "/transfer")
    public Boolean transferBalance(@RequestParam double amount, @RequestParam Long toBankUserId) {
        log.info("IN BankUserController - Active User Transfer ATTEMPTED Balance: {}to User with TransactionId: {}", amount, toBankUserId);

        return bankUserService.transfer(amount, toBankUserId);
    }

    @DeleteMapping(USER_PATH + "/delete")
    public Boolean deleteBankUser() {
        log.info("IN BankUserController - Active User Deleted");

        return bankUserService.delete();
    }

}
