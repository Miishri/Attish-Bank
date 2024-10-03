package org.bank.branch.attish.security.jwt;


import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
public class JwtTokenService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public JwtTokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateToken(Authentication authentication) {
        Instant instant = Instant.now();

        JwtClaimsSet claims =
                JwtClaimsSet.builder()
                        .issuer("self")
                        .issuedAt(instant)
                        .expiresAt(instant.plus(1, ChronoUnit.HOURS))
                        .subject(authentication.getName())
                        .claim("scope", "*") // full access scope
                        .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);

            if (Objects.requireNonNull(jwt.getExpiresAt()).isBefore(Instant.now())) {
                throw new AuthenticationCredentialsNotFoundException("Token expired");
            }

            return true;
        } catch (JwtException ex) {
            throw new AuthenticationCredentialsNotFoundException("Invalid JWT token", ex);
        }
    }

    public String getUsernameFromJWT(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

}
