package com.spd.baraholka.user;

import java.util.Objects;

public class UserAdditionalResource {

    private int id;
    private int userId;
    private String name;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAdditionalResource that = (UserAdditionalResource) o;
        return id == that.id && userId == that.userId && Objects.equals(name, that.name) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, url);
    }

    @Override
    public String toString() {
        return "UserAdditionalResource{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
