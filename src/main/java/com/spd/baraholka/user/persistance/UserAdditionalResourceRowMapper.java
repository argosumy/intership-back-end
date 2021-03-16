package com.spd.baraholka.user.persistance;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserAdditionalResourceRowMapper implements RowMapper<UserAdditionalResource> {

    @Override
    public UserAdditionalResource mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAdditionalResource additionalResource = new UserAdditionalResource();
        additionalResource.setId(rs.getInt("id"));
        additionalResource.setUserId(rs.getInt("user_id"));
        additionalResource.setResourceName(rs.getString("resource_name"));
        additionalResource.setResourceUrl(rs.getString("resource_url"));
        return additionalResource;
    }
}
