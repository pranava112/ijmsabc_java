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
@Table(name = "membership")
@Data
@NoArgsConstructor
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String designation;
    private String membershiptype;
    private String email;
    private String address;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cv;

    // Parameterized constructor
    public Membership(String name, String designation,String membershiptype, String email, String address, byte[] cv) {
        this.name = name;
        this.designation = designation;
        this.membershiptype = membershiptype;
        this.email = email;
        this.address = address;
        this.cv = cv;
    }
}
