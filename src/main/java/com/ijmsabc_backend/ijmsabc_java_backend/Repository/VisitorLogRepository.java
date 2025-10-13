package com.ijmsabc_backend.ijmsabc_java_backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.VisitorLog;



@Repository
public interface VisitorLogRepository extends JpaRepository<VisitorLog, Integer> {
    Optional<VisitorLog> findByDateAndUserId(String date, String userId);
    void deleteByDate(String date);
}