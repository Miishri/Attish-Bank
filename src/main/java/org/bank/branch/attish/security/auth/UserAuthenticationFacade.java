package org.bank.branch.attish.security.auth;

import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.bank.branch.attish.security.user.BankUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAuthenticationFacade implements IUserAuthenticationFacade{

    @Override
    public Authentication getAuthentication() {
        log.info("IN UserAuthenticationFacade - Active User Authentication Fetched");

        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getUsername() {
        return getAuthentication().getName();
    }
}
