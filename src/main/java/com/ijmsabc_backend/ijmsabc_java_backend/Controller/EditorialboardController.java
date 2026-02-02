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

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Editorialboard;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.EditorialboardRepository;




@RestController
@RequestMapping("/api/ijmsabc/editorialboardupload")
@CrossOrigin(origins = {
    "https://www.ijmsabc.org", 
    "http://localhost:5173"    
})

public class EditorialboardController {

    @Autowired
    private  EditorialboardRepository editorialboardRepository;

    // Save new review
    @PostMapping
    public ResponseEntity<Editorialboard> saveReviews(
            @RequestParam("name") String name,
            @RequestParam("designation") String designation,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("source") String source,
            @RequestParam("cv") MultipartFile file) throws IOException {

        Editorialboard editorialboard = new Editorialboard(name, designation, email, address, file.getBytes(),source);
        Editorialboard savedEditorialboard = editorialboardRepository.save(editorialboard);
        return ResponseEntity.ok(savedEditorialboard);
    }

    // Get all reviews
    @GetMapping
    public ResponseEntity<List<Editorialboard>> getAllReviews() {
        return ResponseEntity.ok(editorialboardRepository.findAll());
    }

    // Delete review by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviews(@PathVariable Long id) {
        if (editorialboardRepository.existsById(id)) {
            editorialboardRepository.deleteById(id);
            return ResponseEntity.ok("Editorial Board Repository with id " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Editorial Board Repository not found with id " + id);
        }
    }
}
