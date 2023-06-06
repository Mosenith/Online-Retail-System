package onlineretailsystem.service;

import onlineretailsystem.domain.*;
import onlineretailsystem.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review getReviewById(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        return optionalReview.get();

    }

    public List<Review> getAllReviews(){
        List<Review> reviewList = new ArrayList<Review>();
        reviewRepository.findAll().forEach(review -> reviewList.add(review));
        return reviewList;
    }

    public void deleteReviewByIds(Long itemId) {
        reviewRepository.deleteById(itemId);

    }

    public Review addReview(Long itemId) {
        Customer customer = new Customer("Customer1", "pass", "Stephanie", "Tayo", "@gmail.com","customer");
        Review review = new Review(1, "GoodReview","I enjoyed the product",3 , customer);
        Review review2 = new Review(4, "Small size","The dress was too small",1 , customer);

        reviewRepository.save(review);
        reviewRepository.save(review2);

        return review;
    }


    public Review updateReview(Long itemId, Review updatedReview) {
        Optional<Review> optionalReview = reviewRepository.findById(itemId);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.parse(updatedReview);
            reviewRepository.save(review);
            return review;
        } else {
            return null;
        }
    }







}
