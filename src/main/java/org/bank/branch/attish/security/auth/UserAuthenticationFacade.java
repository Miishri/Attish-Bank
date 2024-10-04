package org.bank.branch.attish.security.auth;

import com.nimbusds.jose.proc.SecurityContext;
import org.bank.branch.attish.security.user.BankUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationFacade implements IUserAuthenticationFacade{

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public BankUserDetails getUserDetails() {
        return (BankUserDetails) getAuthentication().getPrincipal();
    }

    @Override
    public String getUsername() {
        return getUserDetails().getUsername();
    }
}
