package com.example.ch4labs.dto.comment;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class CommentPageResponse {
    private List<CommentResponse> content;
    private Long totalElements;
    private long totalPages;
    private int size;
    private int page;

    public static CommentPageResponse from(Page<CommentResponse> page) {
        return CommentPageResponse.builder()
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .page(page.getNumber())
                .build();
    }
}
