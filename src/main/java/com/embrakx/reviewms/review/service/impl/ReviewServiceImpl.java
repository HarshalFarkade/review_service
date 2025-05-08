package com.embrakx.reviewms.review.service.impl;


import com.embrakx.reviewms.review.entity.Review;
import com.embrakx.reviewms.review.repository.ReviewRepository;
import com.embrakx.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public List<Review> findCompanyById(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {

        if (companyId != null && review != null){
            review.setCompanyId(companyId);
             reviewRepository.save(review);
             return true;
        }else {
            return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);

    }

    @Override
    public boolean updateReview( Long reviewId, Review updateReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
       if (review!=null){
            review.setTitle(updateReview.getTitle());
            review.setDescription(updateReview.getDescription());
            review.setRating(updateReview.getRating());
            review.setCompanyId(updateReview.getCompanyId());
            reviewRepository.save(review);
            return  true;   
       }else{
           return false;
       }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if (review !=null){
           reviewRepository.delete(review);
           return  true;
        }
        return false;
    }
}
