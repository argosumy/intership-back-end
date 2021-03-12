package com.spduniversity.notifications.model;


public class User {
    private int id;
    private String image;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String phoneNumber;
    private String roleInSystem;
    private boolean blockedStatus;
    private String resourcesLink;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoleInSystem() {
        return roleInSystem;
    }

    public void setRoleInSystem(String roleInSystem) {
        this.roleInSystem = roleInSystem;
    }

    public boolean isBlockedStatus() {
        return blockedStatus;
    }

    public void setBlockedStatus(boolean blockedStatus) {
        this.blockedStatus = blockedStatus;
    }

    public String getResourcesLink() {
        return resourcesLink;
    }

    public void setResourcesLink(String resourcesLink) {
        this.resourcesLink = resourcesLink;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                ", role_in_system='" + roleInSystem + '\'' +
                ", blocked_status=" + blockedStatus +
                ", resources_link='" + resourcesLink + '\'' +
                '}';
    }
}
