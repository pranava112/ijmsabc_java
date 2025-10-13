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

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Membership;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.MembershipRepository;


@RestController
@RequestMapping("/api/ijmsabc/membership")
@CrossOrigin(origins = {
    "https://www.ijmsabc.org", 
    "http://localhost:5173"    
})
public class MembershipController {

    @Autowired
    private MembershipRepository membershipRepository;

    // Save new membership
    @PostMapping
    public ResponseEntity<Membership> saveMembership(
            @RequestParam("name") String name,
            @RequestParam("designation") String designation,
            @RequestParam("membershiptype") String membershiptype,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("cv") MultipartFile file) throws IOException {

        Membership membership = new Membership(name, designation, membershiptype, email, address, file.getBytes());
        Membership savedMembership = membershipRepository.save(membership);
        return ResponseEntity.ok(savedMembership);
    }

    // Get all memberships
    @GetMapping
    public ResponseEntity<List<Membership>> getAllMembership() {
        return ResponseEntity.ok(membershipRepository.findAll());
    }

    // Delete membership by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMembership(@PathVariable Long id) {
        if (membershipRepository.existsById(id)) {
            membershipRepository.deleteById(id);
            return ResponseEntity.ok("Membership with id " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Membership not found with id " + id);
        }
    }
}
