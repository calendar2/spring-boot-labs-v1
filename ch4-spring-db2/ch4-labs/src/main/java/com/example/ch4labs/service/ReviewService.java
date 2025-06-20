package com.example.ch4labs.service;

import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.review.*;
import com.example.ch4labs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewCreateRequest request) {
        Review review = request.toDomain();
        Review saved = reviewRepository.save(review);

        return ReviewResponse.from(saved);
    }

//    @Transactional(readOnly = true)
//    public ReviewPageResponse getAllReviews(ReviewSearchRequest searchRequest) {
//        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
//
//        // 검색어에 따른 조건 분기
//        Page<Review> pageReviews;
//        if (searchRequest.getBookTitle() != null) {
//            pageReviews = reviewRepository.findByBookTitle(searchRequest.getBookTitle(), pageable);
//        } else if (searchRequest.getAuthor() != null && searchRequest.getRating() != null) {
//            pageReviews = reviewRepository.findByAuthorAndRating(searchRequest.getAuthor(), searchRequest.getRating(), pageable);
//        } else if (searchRequest.getMinRating() != null && searchRequest.getMaxRating() != null) {
//            pageReviews = reviewRepository.findByRatingBetween(searchRequest.getMinRating(), searchRequest.getMaxRating(), pageable);
//        } else {
//            pageReviews = reviewRepository.findAll(pageable);
//        }
//        Page<ReviewResponse> reviews = pageReviews.map(ReviewResponse::from);
//
//        return ReviewPageResponse.from(reviews.getContent(), searchRequest, reviews.getTotalElements());
//    }

    @Transactional(readOnly = true)
    public ReviewPageResponse getAllReviews(ReviewSearchRequest searchRequest) {
        Page<Review> pageReviews = reviewRepository.search(searchRequest);
        Page<ReviewResponse> reviews = pageReviews.map(ReviewResponse::from);

        return ReviewPageResponse.from(reviews.getContent(), searchRequest, reviews.getTotalElements());
    }

    public ReviewResponse updateReview(Long id, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 리뷰가 존재하지 않습니다."));
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setAuthor(request.getAuthor());
        review.setBookTitle(request.getBookTitle());
        review.setBookAuthor(request.getBookAuthor());
        review.setRating(request.getRating());

        return ReviewResponse.from(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
