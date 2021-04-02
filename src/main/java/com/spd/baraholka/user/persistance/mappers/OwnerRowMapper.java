package com.spd.baraholka.user.persistance.mappers;

import com.spd.baraholka.user.persistance.entities.Owner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OwnerRowMapper implements RowMapper<Owner> {

    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getInt("id"));
        setFullName(rs, owner);
        owner.setEmail(rs.getString("e_mail"));
        owner.setImageUrl(rs.getString("avatar"));
        return owner;
    }

    private void setFullName(ResultSet resultSet, Owner owner) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        stringBuilder.append(firstName).append(" ").append(lastName);
        owner.setFullName(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
    }
}
