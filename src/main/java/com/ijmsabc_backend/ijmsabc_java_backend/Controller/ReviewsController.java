package com.ijmsabc_backend.ijmsabc_java_backend.Controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Reviews;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.ReviewsRepository;


@RestController
@RequestMapping("/api/ijmsabc/reviews")
@CrossOrigin(origins = {
    "https://www.ijmsabc.org", 
    "http://localhost:5173"    
})

public class ReviewsController {

    @Autowired
    private ReviewsRepository reviewsRepository;

    // Save new review
    @PostMapping
    public ResponseEntity<Reviews> saveReviews(
            @RequestParam("name") String name,
            @RequestParam("designation") String designation,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("cv") MultipartFile file) throws IOException {

        Reviews reviews = new Reviews(name, designation, email, address, file.getBytes());
        Reviews savedReviews = reviewsRepository.save(reviews);
        return ResponseEntity.ok(savedReviews);
    }

    // Get all reviews
    @GetMapping
    public ResponseEntity<List<Reviews>> getAllReviews() {
        return ResponseEntity.ok(reviewsRepository.findAll());
    }

    // Delete review by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviews(@PathVariable Long id) {
        if (reviewsRepository.existsById(id)) {
            reviewsRepository.deleteById(id);
            return ResponseEntity.ok("Review with id " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Review not found with id " + id);
        }
    }
}
