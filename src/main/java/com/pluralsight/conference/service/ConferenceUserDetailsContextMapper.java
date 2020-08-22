package com.pluralsight.conference.service;

import com.pluralsight.conference.model.ConferenceUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

@Service
public class ConferenceUserDetailsContextMapper implements UserDetailsContextMapper {

    @Autowired
    private DataSource dataSource;

    private static final String loadUserByUsernameQuery = "select username, password, " +
            "enabled, nickname from users where username = ?";

    @Override
    public UserDetails mapUserFromContext(DirContextOperations dirContextOperations, String s, Collection<? extends GrantedAuthority> collection) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        final ConferenceUserDetails userDetails = new ConferenceUserDetails(
                dirContextOperations.getStringAttribute("uid"),
                "fake",
                Collections.EMPTY_LIST);

        jdbcTemplate.queryForObject(loadUserByUsernameQuery, new RowMapper<ConferenceUserDetails>() {

            @Override
            public ConferenceUserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
                userDetails.setNickname(resultSet.getString("nickname"));
                return userDetails;
            }
        }, dirContextOperations.getStringAttribute("uid"));

        return userDetails;
    }

    @Override
    public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) {

    }
}
