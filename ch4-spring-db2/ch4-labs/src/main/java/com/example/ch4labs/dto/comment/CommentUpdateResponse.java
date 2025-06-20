package com.example.ch4labs.dto.comment;

import com.example.ch4labs.domain.Comment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentUpdateResponse {
    private Long id;
    private String content;
    private String author;
    private Long reviewId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentUpdateResponse from(Comment comment) {
        return CommentUpdateResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .reviewId(comment.getReview().getId())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
