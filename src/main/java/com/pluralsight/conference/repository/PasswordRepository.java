package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Password;
import com.pluralsight.conference.model.ResetToken;

public interface PasswordRepository {
    void saveToken(ResetToken resetToken);

    ResetToken findByToken(String token);

    void update(Password password, String username);
}
