package com.spd.baraholka.image.persistance.entity;

import java.time.*;
import java.util.*;

public class Image {
    private Long id;
    private String url;
    private boolean isAttached;
    private LocalDateTime uploadedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAttached() {
        return isAttached;
    }

    public void setAttached(boolean attached) {
        isAttached = attached;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return isAttached == image.isAttached && Objects.equals(id, image.id) && Objects.equals(url, image.url)
                && Objects.equals(uploadedAt, image.uploadedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, isAttached, uploadedAt);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Image{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append(", isAttached=").append(isAttached);
        sb.append(", uploadedAt=").append(uploadedAt);
        sb.append('}');
        return sb.toString();
    }
}
