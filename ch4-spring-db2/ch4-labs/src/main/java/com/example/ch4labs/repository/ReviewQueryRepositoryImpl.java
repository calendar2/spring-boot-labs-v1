package com.example.ch4labs.repository;

import com.example.ch4labs.domain.QReview;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.review.ReviewSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> search(ReviewSearchRequest request) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 1. 책 제목 키워드 포함
        if (StringUtils.hasText(request.getBookTitle())) {
            builder.and(review.bookTitle.eq(request.getBookTitle()));
        } else if (StringUtils.hasText(request.getBookTitleContains())) {
            builder.and(review.bookTitle.contains(request.getBookTitleContains()));
        }

        // 2. 평점
        if (request.getRating() != null) {
            builder.and(review.rating.eq(request.getRating()));
        } else {
            // 2-1. 평점 범위(minRating)
            if (request.getMinRating() != null) {
                builder.and(review.rating.goe(request.getMinRating()));
            }

            // 2-2. 평점 범위(maxRating)
            if (request.getMaxRating() != null) {
                builder.and(review.rating.loe(request.getMaxRating()));
            }
        }

        // 3. 리뷰 작성자
        if (StringUtils.hasText(request.getAuthor())) {
            builder.and(review.author.eq(request.getAuthor()));
        }

        // 4. 책 저자
        if (StringUtils.hasText(request.getBookAuthor())) {
            builder.and(review.bookAuthor.eq(request.getBookAuthor()));
        }

        // 5. 리뷰 제목
        if (StringUtils.hasText(request.getTitleContains())) {
            builder.and(review.title.contains(request.getTitleContains()));
        }

        // 6. 리뷰 본문
        if (StringUtils.hasText(request.getContentContains())) {
            builder.and(review.content.contains(request.getContentContains()));
        }

        // 7. 정렬 조건
        String[] sorts = request.getSort().split(",");
        OrderSpecifier<?> orderSpecifier = getOrderSpecifier(
                sorts[0], sorts[1], review
        );

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        List<Review> reviews = jpaQueryFactory.selectFrom(review)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = jpaQueryFactory.select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(reviews, pageable, totalCount != null ? totalCount : 0);
    }

    private OrderSpecifier<?> getOrderSpecifier(String field, String direction, QReview review) {
        boolean isAsc = "asc".equalsIgnoreCase(direction);

        return switch (field) {
            case "title" -> isAsc ? review.title.asc() : review.title.desc();
            case "content" -> isAsc ? review.content.asc() : review.content.desc();
            case "author" -> isAsc ? review.author.asc() : review.author.desc();
            case "bookTitle" -> isAsc ? review.bookTitle.asc() : review.bookTitle.desc();
            case "bookAuthor" -> isAsc ? review.bookAuthor.asc() : review.bookAuthor.desc();
            case "rating" -> isAsc ? review.rating.asc() : review.rating.desc();
            default -> isAsc ? review.id.asc() : review.id.desc();
        };
    }

}
