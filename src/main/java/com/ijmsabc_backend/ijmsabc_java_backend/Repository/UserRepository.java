package com.ijmsabc_backend.ijmsabc_java_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
