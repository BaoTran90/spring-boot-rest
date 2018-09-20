package com.baotran.springboot.rest.example.springbootrest.repository;

import com.baotran.springboot.rest.example.springbootrest.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class OAuthRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserEntity getUserDetails(String username) {
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";
        List<UserEntity> users = jdbcTemplate.query(userSQLQuery, new String[] { username }, (resultSet, rowNum) -> {
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPassword(resultSet.getString("PASSWORD"));
            return user;
        });
        if (users.size() > 0) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            grantedAuthoritiesList.add(grantedAuthority);
            users.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
            return users.get(0);
        }
        return null;
    }
}
