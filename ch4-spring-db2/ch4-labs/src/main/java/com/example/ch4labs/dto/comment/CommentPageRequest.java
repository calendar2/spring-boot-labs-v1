package com.example.ch4labs.dto.comment;

import lombok.Data;

@Data
public class CommentPageRequest {
    private String sort;
    private int page;
    private int size;
}
