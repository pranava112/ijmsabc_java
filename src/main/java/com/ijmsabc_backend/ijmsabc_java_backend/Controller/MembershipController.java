package com.ijmsabc_backend.ijmsabc_java_backend.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // ✅ Save new membership
    @PostMapping("/upload")
    public ResponseEntity<?> saveMembership(
            @RequestParam("name") String name,
            @RequestParam("designation") String designation,
            @RequestParam("membershipType") String membershipType,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("source") String source,
            @RequestParam("cv") MultipartFile file
    ) {
        try {
            Membership membership = new Membership(
                    name,
                    designation,
                    membershipType,
                    email,
                    address,
                    file.getBytes(),
                    source
            );

            Membership savedMembership = membershipRepository.save(membership);
            return ResponseEntity.ok(savedMembership);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("CV Upload Failed ❌");
        }
    }

    // ✅ Get all memberships
    @GetMapping
    public ResponseEntity<List<Membership>> getAllMembership() {
        return ResponseEntity.ok(membershipRepository.findAll());
    }

    // ✅ Delete membership by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMembership(@PathVariable Long id) {

        if (membershipRepository.existsById(id)) {
            membershipRepository.deleteById(id);
            return ResponseEntity.ok("Membership with id " + id + " deleted successfully!");
        }

        return ResponseEntity.status(404).body("Membership not found with id " + id);
    }
}
