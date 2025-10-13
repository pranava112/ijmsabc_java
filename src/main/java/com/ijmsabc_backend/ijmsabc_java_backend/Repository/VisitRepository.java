package com.ijmsabc_backend.ijmsabc_java_backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Visit;



@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {
    Optional<Visit> findByDate(String date);
    void deleteByDate(String date);
}