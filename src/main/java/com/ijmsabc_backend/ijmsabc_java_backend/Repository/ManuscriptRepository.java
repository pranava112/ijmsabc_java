package com.ijmsabc_backend.ijmsabc_java_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Manuscript;


@Repository
public interface ManuscriptRepository extends JpaRepository<Manuscript, Long> {
}
