package com.example.ch4labs.dto.comment;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private String content;
    private String author;
}
