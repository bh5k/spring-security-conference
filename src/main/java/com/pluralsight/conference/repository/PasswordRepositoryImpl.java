package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Password;
import com.pluralsight.conference.model.ResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PasswordRepositoryImpl implements PasswordRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public void saveToken(ResetToken resetToken) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("INSERT INTO reset_tokens (email, username, token, expiry_date) VALUES " +
                        "(?,?,?,?)", resetToken.getEmail(),
                resetToken.getUsername(),
                resetToken.getToken(),
                resetToken.calculateExpiryDate(resetToken.EXPIRATION));
    }

    @Override
    public ResetToken findByToken(String token) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        ResetToken resetToken =
                template.queryForObject("select email, username, token, expiry_date from reset_tokens where token = ?",
                        new RowMapper<ResetToken>() {
                            @Override
                            public ResetToken mapRow(ResultSet resultSet, int i) throws SQLException {
                                ResetToken rsToken = new ResetToken();
                                rsToken.setEmail(resultSet.getString("email"));
                                rsToken.setUsername(resultSet.getString("username"));
                                rsToken.setToken(resultSet.getString("token"));
                                rsToken.setExpiryDate(resultSet.getTimestamp("expiry_date"));
                                return rsToken;
                            }
                        },
                        token);
        return resetToken;
    }

    @Override
    public void update(Password password, String username) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE users SET password = ? WHERE username = ?", password.getPassword(), username);
    }
}
