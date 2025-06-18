package com.example.ch4labs.dto;

import lombok.Getter;

@Getter
public class ReviewUpdateRequest {
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private byte rating;
}
