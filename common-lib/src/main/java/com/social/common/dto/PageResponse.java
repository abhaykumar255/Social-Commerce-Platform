package com.social.common.dto;

import java.util.List;

public class PageResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalPages;
    private boolean first;
    private boolean last;
    private boolean empty;
}
