package dev._6.JavaProject;

import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    public Review createReview(String body, String imdbId) {
        Review review = new Review(body);
        return review;
    }
}
