package com.example.mobilebackend1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class PageDto<T> {
    public static final String TICKETS_PAGE = "/tickets?page=";
    public static final String SIZE = "&size=";
    public static final String CONTENT = "&content=";

    private Integer index;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
    private List<T> content;
    private boolean isLast;
    private String previousPage;
    private String nextPage;

    public PageDto(List<T> content, String description, Integer totalPages, Long totalElements, Pageable pageable) {
        this.content = content;
        this.index = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.isLast = Objects.equals(index, totalPages - 1);
        this.previousPage = pageable.hasPrevious() ? TICKETS_PAGE + pageable.previousOrFirst().getPageNumber() + SIZE + pageable.previousOrFirst().getPageSize() + CONTENT + description : null;
        this.nextPage = isLast ? null : TICKETS_PAGE + pageable.next().getPageNumber() + SIZE + pageable.next().getPageSize() + CONTENT + description;
    }
}
