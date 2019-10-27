package com.springboot.angular.coffeesystem.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {
    public static Pageable createPageable(int page, int size, String sort, String sortColumn) {
        Sort sortable;
        if (sort.trim().equalsIgnoreCase("asc"))
            sortable = Sort.by(sortColumn).ascending();
        else
            sortable = Sort.by(sortColumn).descending();
        Pageable pageable = PageRequest.of(page, size, sortable);
        return pageable;
    }
}
