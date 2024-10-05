package org.bank.branch.attish.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.bank.branch.attish.security.user.BankUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final JwtTokenService jwtTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BankUserRepository bankUserRepository;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public AuthController(JwtTokenService jwtTokenService,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          BankUserRepository bankUserRepository) {
        this.jwtTokenService = jwtTokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.bankUserRepository = bankUserRepository;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(SecurityContext currentContext, HttpServletRequest request, HttpServletResponse response, @RequestBody BankUser bankUser) {
        log.info("USER LOGGED IN WITH BODY : {}", bankUser);

        if (!bankUserRepository.existsBankUserByUsername(bankUser.getUsername())){
            return new ResponseEntity<>("USER NOT FOUND", HttpStatus.UNAUTHORIZED);
        }

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("*"));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(new BankUserDetails(bankUser), authorities);

        currentContext.setAuthentication(authentication);
        securityContextRepository.saveContext(currentContext, request, response);

        String token = jwtTokenService.generateToken(authentication);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody BankUser bankUser) {
        if (bankUserRepository.existsBankUserByUsername(bankUser.getUsername())) {
            return new ResponseEntity<>("Username Taken!", HttpStatus.BAD_REQUEST);
        }

        SecureRandom random = new SecureRandom();
        long n = (random.nextLong() ^ System.currentTimeMillis());

        BankUser user = BankUser.builder()
                .username(bankUser.getUsername())
                .password(bCryptPasswordEncoder.encode((bankUser.getPassword())))
                .balance(bankUser.getBalance())
                .birthdate(bankUser.getBirthdate())
                .firstName(bankUser.getFirstName())
                .lastName(bankUser.getLastName())
                .transactionId(n)
                .build();

        BankUser savedBankUser = bankUserRepository.save(user);

        return new ResponseEntity<>(savedBankUser, HttpStatus.OK);
    }
}
