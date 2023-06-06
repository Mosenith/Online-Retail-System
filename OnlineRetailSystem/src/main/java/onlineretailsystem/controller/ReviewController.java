package onlineretailsystem.controller;


import onlineretailsystem.domain.Order;
import onlineretailsystem.domain.Review;
import onlineretailsystem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;



    @GetMapping("/items/{itemId}/reviews")
    public List<Review> getAllReviews(@PathVariable("itemId") Long itemId){
        return reviewService.getAllReviews();
    }


    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteReviewByIds(@PathVariable("itemId") Long itemId){
        reviewService.deleteReviewByIds(itemId);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }


    @PostMapping(("/items/{itemId}/addingReview"))
    public ResponseEntity<Review> addReview(@PathVariable("itemId") Long itemId) {
        Review review = reviewService.addReview(itemId);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }


    @PutMapping("/{itemId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long itemId, @RequestBody Review review) {
        reviewService.updateReview(itemId, review);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }












}
