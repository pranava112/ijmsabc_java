// package com.ijmsabc_backend.ijmsabc_java_backend.Entity;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Lob;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "manuscripts")
// public class Manuscript {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String name;  

//     @Column(nullable = false)
//     private String email;

//     @Column(nullable = false)
//     private String phone;

//     @Column(nullable = false)
//     private String title;

//     @Column(columnDefinition = "TEXT", nullable = false)
//     private String abst;   

//     @Column(length = 500)
//     private String kwords;

//     @Lob
//     @Column(name = "pdf_doc", columnDefinition = "LONGBLOB", nullable = false)
//     private byte[] pdfDoc;

//     // ✅ Default constructor for JPA
//     public Manuscript() {}

//     public Manuscript(String name, String email, String phone, String title, String abst,
//                       String kwords, byte[] pdfDoc) {
//         this.name = name;
//         this.email = email;
//         this.phone = phone;
//         this.title = title;
//         this.abst = abst;
//         this.kwords = kwords;
//         this.pdfDoc = pdfDoc;
//     }

//     // Getters & Setters
//     public Long getId() { return id; }

//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getPhone() { return phone; }
//     public void setPhone(String phone) { this.phone = phone; }

//     public String getTitle() { return title; }
//     public void setTitle(String title) { this.title = title; }

//     public String getAbst() { return abst; }
//     public void setAbst(String abst) { this.abst = abst; }

//     public String getKwords() { return kwords; }
//     public void setKwords(String kwords) { this.kwords = kwords; }

//     public byte[] getPdfDoc() { return pdfDoc; }
//     public void setPdfDoc(byte[] pdfDoc) { this.pdfDoc = pdfDoc; }
// }



// package com.ijmsabc_backend.ijmsabc_java_backend.Entity;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Lob;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "manuscripts")
// public class Manuscript {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false)
//     private String name;

//     @Column(nullable = false)
//     private String email;

//     @Column(nullable = false)
//     private String phone;

//     @Column(nullable = false)
//     private String title;

//     @Column(columnDefinition = "TEXT", nullable = false)
//     private String abst;

//     @Column(length = 500)
//     private String kwords;

//     @Lob
//     @Column(name = "pdf_doc", columnDefinition = "LONGBLOB", nullable = false)
//     private byte[] pdfDoc;

//     public Manuscript() {}

//     public Manuscript(String name, String email, String phone, String title, String abst, String kwords, byte[] pdfDoc) {
//         this.name = name;
//         this.email = email;
//         this.phone = phone;
//         this.title = title;
//         this.abst = abst;
//         this.kwords = kwords;
//         this.pdfDoc = pdfDoc;
//     }

//     // Getters & Setters
//     public Long getId() { return id; }
//     public String getName() { return name; }
//     public void setName(String name) { this.name = name; }
//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }
//     public String getPhone() { return phone; }
//     public void setPhone(String phone) { this.phone = phone; }
//     public String getTitle() { return title; }
//     public void setTitle(String title) { this.title = title; }
//     public String getAbst() { return abst; }
//     public void setAbst(String abst) { this.abst = abst; }
//     public String getKwords() { return kwords; }
//     public void setKwords(String kwords) { this.kwords = kwords; }
//     public byte[] getPdfDoc() { return pdfDoc; }
//     public void setPdfDoc(byte[] pdfDoc) { this.pdfDoc = pdfDoc; }
// }



package com.ijmsabc_backend.ijmsabc_java_backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "manuscripts")
public class Manuscript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String abst;

    @Column(length = 500)
    private String kwords;

    @Lob
    @Column(name = "pdf_doc", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] pdfDoc;

    public Manuscript() {}

    public Manuscript(String name, String email, String phone, String title, String abst, String kwords, byte[] pdfDoc) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.abst = abst;
        this.kwords = kwords;
        this.pdfDoc = pdfDoc;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAbst() { return abst; }
    public void setAbst(String abst) { this.abst = abst; }
    public String getKwords() { return kwords; }
    public void setKwords(String kwords) { this.kwords = kwords; }
    public byte[] getPdfDoc() { return pdfDoc; }
    public void setPdfDoc(byte[] pdfDoc) { this.pdfDoc = pdfDoc; }
}
