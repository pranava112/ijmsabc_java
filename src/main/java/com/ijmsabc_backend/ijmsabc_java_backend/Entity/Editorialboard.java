package com.ijmsabc_backend.ijmsabc_java_backend.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "editorialboard")
@Data
@NoArgsConstructor
public class Editorialboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String designation;
    private String email;
    private String address;
    private String source;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cv;

    // Parameterized constructor
    public Editorialboard(String name, String designation, String email, String address, byte[] cv,String source) {
        this.name = name;
        this.designation = designation;
        this.email = email;
        this.address = address;
        this.cv = cv;
        this.source=source;
    }
}
