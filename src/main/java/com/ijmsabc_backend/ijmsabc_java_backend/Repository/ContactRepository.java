package com.ijmsabc_backend.ijmsabc_java_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long> {
}
