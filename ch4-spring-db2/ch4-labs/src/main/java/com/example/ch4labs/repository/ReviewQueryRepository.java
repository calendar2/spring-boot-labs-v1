package com.example.ch4labs.repository;

import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.review.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewQueryRepository {
    Page<Review> search(ReviewSearchRequest request);
}
