package com.example.ch4labs.dto;

import com.example.ch4labs.domain.Review;
import lombok.Getter;

@Getter
public class ReviewCreateRequest {
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private byte rating;

    public Review toDomain() {
        Review review = new Review();
        review.setTitle(this.title);
        review.setContent(this.content);
        review.setAuthor(this.author);
        review.setBookTitle(this.bookTitle);
        review.setBookAuthor(this.bookAuthor);
        review.setRating(this.rating);

        return review;
    }
}
