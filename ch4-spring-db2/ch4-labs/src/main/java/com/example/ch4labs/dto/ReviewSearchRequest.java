package com.example.ch4labs.dto;

import lombok.Data;

@Data
public class ReviewSearchRequest {
    private String author;
    private String bookTitle;
    private String keyword;
    private Integer rating;
    private Integer minRating;
    private Integer maxRating;
    private int page = 0;
    private int size = 10;
}
