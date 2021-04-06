package com.spd.baraholka.image.controller.dto;

import java.util.*;

public class ImageDto {

    private final long id;
    private final String url;

    public ImageDto(long id, String url) {
        this.id = id;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImageDto imageDto = (ImageDto) o;
        return id == imageDto.id && url.equals(imageDto.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ImageDto{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
