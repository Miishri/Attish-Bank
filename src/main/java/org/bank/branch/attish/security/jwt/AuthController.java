package org.bank.branch.attish.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenService jwtTokenService;

    public String token(Authentication authentication) {
        return jwtTokenService.generateToken(authentication);
    }
}
