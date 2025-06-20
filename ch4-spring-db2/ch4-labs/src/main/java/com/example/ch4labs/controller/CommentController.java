package com.example.ch4labs.controller;

import com.example.ch4labs.dto.comment.*;
import com.example.ch4labs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long reviewId,
            @RequestBody CommentCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.createComment(reviewId, request));
    }

    @GetMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentPageResponse> getComments(
            @PathVariable Long reviewId,
            @ModelAttribute CommentPageRequest request
    ) {
        return ResponseEntity.ok(commentService.getComments(reviewId, request));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentUpdateResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest request
    ) {
        return ResponseEntity.ok(commentService.updateComment(commentId, request));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
