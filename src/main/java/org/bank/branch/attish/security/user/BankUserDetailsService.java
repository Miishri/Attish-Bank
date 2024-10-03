package org.bank.branch.attish.security.user;

import org.bank.branch.attish.models.BankUser;
import org.bank.branch.attish.respositories.BankUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BankUserDetailsService implements UserDetailsService {

    private final BankUserRepository bankUserRepository;

    public BankUserDetailsService(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankUser user = bankUserRepository.findBankUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new BankUserDetails(user);
    }
}
