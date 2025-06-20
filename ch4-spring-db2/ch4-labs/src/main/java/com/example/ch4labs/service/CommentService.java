package com.example.ch4labs.service;

import com.example.ch4labs.domain.Comment;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.comment.*;
import com.example.ch4labs.repository.CommentRepository;
import com.example.ch4labs.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public CommentResponse createComment(Long reviewId, CommentCreateRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .review(review)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return CommentResponse.from(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public CommentPageResponse getComments(Long reviewId, CommentPageRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("리뷰를 찾을 수 없습니다."));

        String[] sorts = request.getSort().split(",");
        Pageable pageable;
        if (sorts[1].equals("asc")) {
            pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(sorts[0]).ascending());
        } else {
            pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(sorts[0]).descending());
        }

        Page<Comment> page = commentRepository.findByReviewId(review.getId(), pageable);

        return CommentPageResponse.from(page.map(CommentResponse::from));
    }

    public CommentUpdateResponse updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        return CommentUpdateResponse.from(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        commentRepository.delete(comment);
    }
}
