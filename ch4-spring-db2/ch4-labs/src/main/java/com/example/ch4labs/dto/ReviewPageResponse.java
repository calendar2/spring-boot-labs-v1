package com.example.ch4labs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewPageResponse {
    private int page;
    private int size;
    private long totalCount;
    private int totalPages;
    private List<ReviewResponse> reviews;

    public static ReviewPageResponse from(List<ReviewResponse> reviews, ReviewSearchRequest search, Long count) {
        int totalPages = (int) Math.ceil((double) count / search.getSize());
        return new ReviewPageResponse(
                search.getPage(),
                search.getSize(),
                count,
                totalPages,
                reviews
        );
    }
}
