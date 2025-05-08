package com.embrakx.reviewms.review.service;


import com.embrakx.reviewms.review.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    List<Review> getAllReview(Long companyId);

    List<Review> findCompanyById(Long companyId);

    boolean   createReview(Long companyId ,Review review );

    Review getReview(Long reviewId);

    boolean updateReview(Long reviewId, Review updateReview);
    boolean  deleteReview(Long reviewId);
}
