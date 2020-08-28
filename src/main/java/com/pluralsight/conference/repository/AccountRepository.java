package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.model.ConferenceUserDetails;
import com.pluralsight.conference.model.VerificationToken;

public interface AccountRepository {
    public Account create (Account account);
    public Account delete (Account account);
    public VerificationToken findByToken(String token);
    public void saveToken(VerificationToken token);
    public Account findByUsername(String username);
    public void deleteToken(VerificationToken token);
    public void createUserDetails(ConferenceUserDetails userDetails);
    public void createAuthorities(ConferenceUserDetails userDetails);
}
