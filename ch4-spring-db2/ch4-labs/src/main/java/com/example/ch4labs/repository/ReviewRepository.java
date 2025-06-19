package com.example.ch4labs.repository;

import com.example.ch4labs.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {
    // 1. 도서 제목 키워드 검색 + 페이징
    @Query("SELECT r FROM Review r WHERE r.bookTitle LIKE %:bookTitle%")
    Page<Review> findByBookTitle(@Param("bookTitle") String bookTitle, Pageable pageable);

    // 2. 작성자 + 평점 필터링
    @Query("SELECT r FROM Review r WHERE r.author = :author AND r.rating = :rating")
    Page<Review> findByAuthorAndRating(@Param("author") String author, @Param("rating") int rating, Pageable pageable);

    // 3. 평점 범위 검색
    @Query("SELECT r FROM Review r WHERE r.rating > :minRating AND r.rating <= :maxRating")
    Page<Review> findByRatingBetween(@Param("minRating") int minRating, @Param("maxRating") int maxRating, Pageable pageable);
}
