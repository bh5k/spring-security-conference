package com.pluralsight.conference.service;

import com.pluralsight.conference.model.Password;
import com.pluralsight.conference.model.ResetToken;

public interface PasswordService {

    void createResetToken(Password password, String token);

    boolean confirmResetToken(ResetToken token);

    void update(Password password, String username);

}
