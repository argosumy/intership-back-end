package com.spd.baraholka.pagination.entities;

import com.spd.baraholka.advertisements.persistance.Advertisement;

import java.util.List;

public class PageRequest<T> {

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private List<T> content;

    public static PageRequest<Advertisement> of(int pageNumber, int pageSize, int totalPages, List<Advertisement> content) {
        PageRequest<Advertisement> pageRequest = new PageRequest<>();
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
}
