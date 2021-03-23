package com.spd.baraholka.image.validation;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class ErrorDto {

    private final int status;
    private final String detail;
    private final LocalDateTime timeStamp;
    private final String path;

    public ErrorDto(int status, String detail, LocalDateTime timeStamp, String path) {
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorDto errorDto = (ErrorDto) o;
        return status == errorDto.status &&
                timeStamp == errorDto.timeStamp &&
                detail.equals(errorDto.detail) &&
                path.equals(errorDto.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, detail, timeStamp, path);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ErrorDto.class.getSimpleName() + "[", "]")
                .add("status=" + status)
                .add("detail='" + detail + "'")
                .add("timeStamp=" + timeStamp)
                .add("path='" + path + "'")
                .toString();
    }
}
