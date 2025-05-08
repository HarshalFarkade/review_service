package com.embrakx.reviewms.review.controller;


import com.embrakx.reviewms.review.entity.Review;
import com.embrakx.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllRepository(@RequestParam Long companyId){
        List<Review> review= reviewService.getAllReview(companyId);
        return new ResponseEntity<>(review,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long  companyId,@RequestBody Review review){
       boolean isReviewSaved = reviewService.createReview(companyId,review);
       if (isReviewSaved){
           return new ResponseEntity<>("Review Added Successfully",HttpStatus.CREATED);
       }else {
           return new ResponseEntity<>("No Company found with companyId",HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewsByCompanyIdAndReviewsId(@PathVariable Long  reviewId){
    return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK) ;
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(reviewId,review);
        if (isReviewUpdated){
            return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No Review found",HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted  = reviewService.deleteReview(reviewId);
           if(isReviewDeleted){
               return new ResponseEntity<>("Review Deleted !!!",HttpStatus.OK);
           }else {
               return new ResponseEntity<>("No Review Found !!!",HttpStatus.NOT_FOUND);
           }
    }
}
