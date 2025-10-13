package com.ijmsabc_backend.ijmsabc_java_backend.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Visit;
import com.ijmsabc_backend.ijmsabc_java_backend.Service.VisitService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ijmsabc/visitors")
@CrossOrigin(origins = {
    "https://www.ijmsabc.org", 
    "http://localhost:5173"
})
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    // ✅ Record unique visit
    @PostMapping("/visit")
    public Visit recordVisit(HttpServletRequest request) {
        String userIp = request.getRemoteAddr(); // use IP as identifier
        return visitService.recordVisit(userIp);
    }

    // ✅ Get all stats
    @GetMapping("/stats")
    public List<Visit> getStats() {
        return visitService.getAllStats();
    }

    // ✅ Delete all visit records and logs
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllVisits() {
        visitService.deleteAllVisits();
        return ResponseEntity.ok("All visit records and logs deleted successfully.");
    }

    // ✅ Delete visit and logs by date (e.g., /delete/2025-10-07)
    @DeleteMapping("/delete/{date}")
    public ResponseEntity<String> deleteVisitByDate(@PathVariable String date) {
        boolean deleted = visitService.deleteVisitByDate(date);
        if (deleted) {
            return ResponseEntity.ok("Visit record for " + date + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

