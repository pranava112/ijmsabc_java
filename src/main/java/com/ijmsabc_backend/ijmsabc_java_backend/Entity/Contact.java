package com.ijmsabc_backend.ijmsabc_java_backend.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ✅ Use Long (matches repository)

    private String name;
    private String email;
    private String phone;
    private String address;
    private String message;
}
