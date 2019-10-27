package com.springboot.angular.coffeesystem.dto;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public class PagingResponseDto<T> {

    private int statusCode;

    private String errorMessage;

    private String details;

    private Object content;

    private Date timestamp;

    private Pageable pageable;

    private long totalElements;

    private int totalPages;

    public PagingResponseDto(List<T> content, Long totalElements, int totalPages, Pageable pageable) {
        this.statusCode = 200;
        this.errorMessage = null;
        this.details = null;
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageable = pageable;
        this.timestamp = new Date();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
