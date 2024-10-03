package org.bank.branch.attish.security.jwt;

import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.models.UserTransactions;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.bank.branch.attish.respositories.UserTransactionsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BankUserRepository bankUserRepository;
    private final UserTransactionsRepository transactionsRepository;

    public AuthController(JwtTokenService jwtTokenService, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder, BankUserRepository bankUserRepository, UserTransactionsRepository transactionsRepository) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.bankUserRepository = bankUserRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody BankUser user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenService.generateToken(authentication);
        return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
    }


    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody BankUser bankUser) {
        if (bankUserRepository.existsBankUserByUsername(bankUser.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        List<UserTransactions> userTransactions = transactionsRepository.saveAll(bankUser.getUserTransactions());

        BankUser user = BankUser.builder()
                .username(bankUser.getUsername())
                .password(bCryptPasswordEncoder.encode((bankUser.getPassword())))
                .balance(bankUser.getBalance())
                .birthdate(bankUser.getBirthdate())
                .firstName(bankUser.getFirstName())
                .lastName(bankUser.getLastName())
                .tokenExpired(false)
                .userTransactions(userTransactions)
                .build();

        bankUserRepository.save(user);

        return new ResponseEntity<>("Registration Completed!", HttpStatus.OK);
    }
}
