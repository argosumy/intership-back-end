package com.spd.baraholka.pagination.entities;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableList;

public class PageRequest<T> {

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private List<T> content;

    public static <T> PageRequest<T> of(int pageNumber, int pageSize, int totalPages, List<T> content) {
        PageRequest<T> pageRequest = new PageRequest<>();
        pageRequest.setPageNumber(pageNumber);
        pageRequest.setPageSize(pageSize);
        pageRequest.setTotalPages(totalPages);
        pageRequest.setContent(content);
        return pageRequest;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public <R> PageRequest<R> map(Function<T, R> mapFunction) {
        return this.content.stream()
                .map(mapFunction)
                .collect(collectingAndThen(toUnmodifiableList(), this::replaceContent));
    }

    private <R> PageRequest<R> replaceContent(List<R> content) {
        return PageRequest.of(
                this.pageNumber,
                this.pageSize,
                this.totalPages,
                content
        );
    }


}
