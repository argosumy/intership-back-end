package com.spd.baraholka.views;

import java.time.LocalDateTime;
import java.util.Objects;

public class View {
    private int id;
    private int userId;
    private int advertisementId;
    private LocalDateTime viewedAt;

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

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        View view = (View) o;
        return id == view.id && userId == view.userId && advertisementId == view.advertisementId && Objects.equals(viewedAt, view.viewedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, advertisementId, viewedAt);
    }

    @Override
    public String toString() {
        return "View{" +
                "id=" + id +
                ", userId=" + userId +
                ", advertisementId=" + advertisementId +
                ", viewedAt=" + viewedAt +
                '}';
    }
}
