package com.ijmsabc_backend.ijmsabc_java_backend.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Announcement;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.AnnouncementRepository;



@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    public Announcement createAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Optional<Announcement> getAnnouncementById(int id) {
        return announcementRepository.findById(id);
    }

    public Announcement updateAnnouncement(int id, Announcement updatedAnnouncement) {
        return announcementRepository.findById(id)
            .map(existing -> {
                existing.setInformation(updatedAnnouncement.getInformation());
                return announcementRepository.save(existing);
            }).orElse(null);
    }

    public boolean deleteAnnouncement(int id) {
        if (announcementRepository.existsById(id)) {
            announcementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}