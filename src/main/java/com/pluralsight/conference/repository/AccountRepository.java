package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.model.ConferenceUserDetails;
import com.pluralsight.conference.model.VerificationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface AccountRepository {
    public Account create (Account account);

    void saveToken(VerificationToken verificationToken);

    VerificationToken findByToken(String token);

    Account findByUsername(String username);

    void createUserDetails(ConferenceUserDetails userDetails);

    void createAuthorities(ConferenceUserDetails userDetails);

    void delete(Account account);

    void deleteToken(String token);
}
