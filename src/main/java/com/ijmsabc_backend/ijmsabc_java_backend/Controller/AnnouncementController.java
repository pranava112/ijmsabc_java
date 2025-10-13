package com.ijmsabc_backend.ijmsabc_java_backend.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Announcement;
import com.ijmsabc_backend.ijmsabc_java_backend.Service.AnnouncementService;



@RestController
@RequestMapping("/api/ijmsabc/announcement")
// ✅ Allow requests from your production frontend domain
@CrossOrigin(origins = {
    "https://www.ijmsabc.org", 
    "http://localhost:5173"    
})
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    // ✅ Create Announcement
    @PostMapping
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        Announcement savedAnnouncement = announcementService.createAnnouncement(announcement);
        return ResponseEntity.ok(savedAnnouncement);
    }

    // ✅ Get All Announcements
    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }

    // ✅ Get Announcement By ID
    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable int id) {
        Optional<Announcement> announcement = announcementService.getAnnouncementById(id);
        return announcement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Update Announcement
    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable int id, @RequestBody Announcement updatedAnnouncement) {
        Announcement updated = announcementService.updateAnnouncement(id, updatedAnnouncement);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ✅ Delete Announcement
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable int id) {
        boolean deleted = announcementService.deleteAnnouncement(id);
        return deleted ? ResponseEntity.ok("Announcement deleted successfully") : ResponseEntity.notFound().build();
    }
}
