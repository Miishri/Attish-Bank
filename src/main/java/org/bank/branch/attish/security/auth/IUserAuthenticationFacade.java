package org.bank.branch.attish.security.auth;

import org.bank.branch.attish.security.user.BankUserDetails;
import org.springframework.security.core.Authentication;

public interface IUserAuthenticationFacade {
    Authentication getAuthentication();
    String getUsername();
}
