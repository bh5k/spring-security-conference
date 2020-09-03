package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.model.ConferenceUserDetails;
import com.pluralsight.conference.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public Account create(Account account) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("INSERT INTO accounts (username, password, email, firstname, lastname) VALUES " +
                        "(?,?,?,?,?)", account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName());

        return account;
    }

    @Override
    public void saveToken(VerificationToken verificationToken) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("INSERT INTO verification_tokens (username , token, expiry_date) VALUES " +
                        "(?,?,?)", verificationToken.getUsername(),
                verificationToken.getToken(),
                verificationToken.calculateExpiryDate(verificationToken.EXPIRATION));

    }

    @Override
    public VerificationToken findByToken(String token) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        VerificationToken verificationToken =
                template.queryForObject("select username, token, expiry_date from verification_tokens where token = ?",
                        new RowMapper<VerificationToken>() {
                            @Override
                            public VerificationToken mapRow(ResultSet resultSet, int i) throws SQLException {
                                VerificationToken rsToken = new VerificationToken();
                                rsToken.setUsername(resultSet.getString("username"));
                                rsToken.setToken(resultSet.getString("token"));
                                rsToken.setExpiryDate(resultSet.getTimestamp("expiry_date"));
                                return rsToken;
                            }
                        },
                        token);
        return verificationToken;
    }

    @Override
    public Account findByUsername(String username) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Account account =
                template.queryForObject("select username, firstname, lastname, " +
                                "password from accounts where username = ?",
                        new RowMapper<Account>() {
                            @Override
                            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                                Account account = new Account();
                                account.setUsername(resultSet.getString("username"));
                                account.setFirstName(resultSet.getString("firstname"));
                                account.setLastName(resultSet.getString("lastname"));
                                account.setPassword(resultSet.getString("password"));
                                return account;
                            }
                        },
                        username);
        return account;
    }

    @Override
    public void createUserDetails(ConferenceUserDetails userDetails) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("INSERT INTO users (username , password, enabled) VALUES " +
                        "(?,?,?)", userDetails.getUsername(),
                userDetails.getPassword(),
                1);

    }

    @Override
    public void createAuthorities(ConferenceUserDetails userDetails) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Iterator<GrantedAuthority> itr = userDetails.getAuthorities().iterator();
        while(itr.hasNext()) {
            template.update("INSERT INTO authorities(username, authority) VALUES (?, ?)",
                    userDetails.getUsername(),
                    itr.next().getAuthority());
        }
    }

    @Override
    public void delete(Account account) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("DELETE FROM accounts where username = ?", account.getUsername());
    }

    @Override
    public void deleteToken(String token) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("DELETE FROM verification_tokens where token = ?", token);
    }
}
