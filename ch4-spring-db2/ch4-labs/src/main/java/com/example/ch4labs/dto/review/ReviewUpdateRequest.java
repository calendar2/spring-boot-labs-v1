package com.example.ch4labs.dto.review;

import lombok.Getter;

@Getter
public class ReviewUpdateRequest {
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private Integer rating;
}
