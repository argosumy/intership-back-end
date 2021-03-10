package com.spduniversity.notifications.model;

import lombok.Data;



public class User {
    private int id;
    private String image;
    private String first_name;
    private String last_name;
    private String email;
    private String position;
    private String phone_number;
    private String role_in_system;
    private boolean blocked_status;
    private String resources_link;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRole_in_system() {
        return role_in_system;
    }

    public void setRole_in_system(String role_in_system) {
        this.role_in_system = role_in_system;
    }

    public boolean isBlocked_status() {
        return blocked_status;
    }

    public void setBlocked_status(boolean blocked_status) {
        this.blocked_status = blocked_status;
    }

    public String getResources_link() {
        return resources_link;
    }

    public void setResources_link(String resources_link) {
        this.resources_link = resources_link;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", role_in_system='" + role_in_system + '\'' +
                ", blocked_status=" + blocked_status +
                ", resources_link='" + resources_link + '\'' +
                '}';
    }
}
