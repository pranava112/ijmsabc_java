package com.ijmsabc_backend.ijmsabc_java_backend.Service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Visit;
import com.ijmsabc_backend.ijmsabc_java_backend.Entity.VisitorLog;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.VisitRepository;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.VisitorLogRepository;



@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final VisitorLogRepository visitorLogRepository;

    public VisitService(VisitRepository visitRepository, VisitorLogRepository visitorLogRepository) {
        this.visitRepository = visitRepository;
        this.visitorLogRepository = visitorLogRepository;
    }

    // ✅ Record a unique visit per user (by IP) per day
    public Visit recordVisit(String userId) {
        String today = LocalDate.now().toString();

        // Check if this user already logged today
        boolean alreadyVisited = visitorLogRepository.findByDateAndUserId(today, userId).isPresent();

        if (!alreadyVisited) {
            // Record visit
            Visit visit = visitRepository.findByDate(today).orElse(new Visit());

            if (visit.getDate() == null) {
                visit.setDate(today);
                visit.setCount(1);
            } else {
                visit.setCount(visit.getCount() + 1);
            }
            visitRepository.save(visit);

            // Save log to prevent duplicate
            VisitorLog log = new VisitorLog();
            log.setDate(today);
            log.setUserId(userId);
            visitorLogRepository.save(log);

            return visit;
        }

        // Return today's record without increment
        return visitRepository.findByDate(today).orElseGet(() -> {
            Visit v = new Visit();
            v.setDate(today);
            v.setCount(0);
            return v;
        });
    }

    // ✅ Fetch all visit statistics
    public List<Visit> getAllStats() {
        return visitRepository.findAll();
    }

    // ✅ Delete all visits and visitor logs
    public void deleteAllVisits() {
        visitorLogRepository.deleteAll();
        visitRepository.deleteAll();
    }

    // ✅ Delete visit and logs by date
    public boolean deleteVisitByDate(String date) {
        boolean exists = visitRepository.findByDate(date).isPresent();
        if (exists) {
            visitorLogRepository.deleteByDate(date);
            visitRepository.deleteByDate(date);
        }
        return exists;
    }
}
