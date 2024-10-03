package org.bank.branch.attish.security.jwt;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtTokenService {
    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {
        Instant instant = Instant.now();

        JwtClaimsSet claims =
                JwtClaimsSet.builder()
                        .issuer("self")
                        .issuedAt(instant)
                        .expiresAt(instant.plus(1, ChronoUnit.HOURS))
                        .subject(authentication.getName())
                        .claim("scope", "*")
                        .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
