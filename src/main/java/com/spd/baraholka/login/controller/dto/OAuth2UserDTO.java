package com.spd.baraholka.login.controller.dto;

import java.util.Objects;

public class OAuth2UserDTO {

    private final String email;
    private final String firstName;
    private final String lastName;
    private final String avatar;

    public OAuth2UserDTO(String email, String firstName, String lastName, String avatar) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        OAuth2UserDTO oAuth2UserDto = (OAuth2UserDTO) object;
        return Objects.equals(email, oAuth2UserDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "OAuth2UserDto{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}