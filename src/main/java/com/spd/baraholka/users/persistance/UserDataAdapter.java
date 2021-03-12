package com.spd.baraholka.users.persistance;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserDataAdapter implements PersistenceUserService {

    private final UserRepository userRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDataAdapter(UserRepository userRepository, NamedParameterJdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }
}
