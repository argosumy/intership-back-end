package com.spd.baraholka.views;

import java.time.LocalDateTime;
import java.util.Objects;

public class View {
    private final int id;
    private final int userId;
    private final int advertisementId;
    private final LocalDateTime viewedAt;

    public View(int id, int userId, int advertisementId, LocalDateTime viewedAt) {
        this.id = id;
        this.userId = userId;
        this.advertisementId = advertisementId;
        this.viewedAt = viewedAt;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getAdvertisementId() {
        return advertisementId;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
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
