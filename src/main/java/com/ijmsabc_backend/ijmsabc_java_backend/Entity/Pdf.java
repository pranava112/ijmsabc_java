// // package com.ijmsabc_backend.ijmsabc_java_backend.Entity;

// // import jakarta.persistence.*;

// // @Entity
// // @Table(name = "pdfs")
// // public class Pdf {

// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;

// //     @Column(nullable = false, length = 255)
// //     private String title;

// //     @Lob
// //     @Column(name = "pdf_doc", columnDefinition = "LONGBLOB", nullable = false)
// //     private byte[] pdfDoc;

// //     @Column(length = 50)
// //     private String volume;

// //     @Column(name = "issue_no", length = 50)
// //     private String issueNo;

// //     @Column(name = "pub_year", length = 10)
// //     private String pubYear;

// //     @Column(name = "doc_type", length = 100)
// //     private String issueType;

// //     @Column(length = 255)
// //     private String author;

// //     @Column(length = 255)
// //     private String doi;

// //     @Column(length = 255)
// //     private String source;

// //     // ✅ Default Constructor
// //     public Pdf() {}

// //     // ✅ Parameterized Constructor
// //     public Pdf(String title, byte[] pdfDoc, String volume,
// //                String issueNo, String pubYear, String issueType,
// //                String author, String doi, String source) {

// //         this.title = title;
// //         this.pdfDoc = pdfDoc;
// //         this.volume = volume;
// //         this.issueNo = issueNo;
// //         this.pubYear = pubYear;
// //         this.issueType = issueType;
// //         this.author = author;
// //         this.doi = doi;
// //         this.source = source;
// //     }

// //     // ✅ Getters & Setters

// //     public Long getId() {
// //         return id;
// //     }

// //     public String getTitle() {
// //         return title;
// //     }

// //     public void setTitle(String title) {
// //         this.title = title;
// //     }

// //     public byte[] getPdfDoc() {
// //         return pdfDoc;
// //     }

// //     public void setPdfDoc(byte[] pdfDoc) {
// //         this.pdfDoc = pdfDoc;
// //     }

// //     public String getVolume() {
// //         return volume;
// //     }

// //     public void setVolume(String volume) {
// //         this.volume = volume;
// //     }

// //     public String getIssueNo() {
// //         return issueNo;
// //     }

// //     public void setIssueNo(String issueNo) {
// //         this.issueNo = issueNo;
// //     }

// //     public String getPubYear() {
// //         return pubYear;
// //     }

// //     public void setPubYear(String pubYear) {
// //         this.pubYear = pubYear;
// //     }

// //     public String getIssueType() {
// //         return issueType;
// //     }

// //     public void setIssueType(String issueType) {
// //         this.issueType = issueType;
// //     }

// //     public String getAuthor() {
// //         return author;
// //     }

// //     public void setAuthor(String author) {
// //         this.author = author;
// //     }

// //     public String getDoi() {
// //         return doi;
// //     }

// //     public void setDoi(String doi) {
// //         this.doi = doi;
// //     }

// //     public String getSource() {
// //         return source;
// //     }

// //     public void setSource(String source) {
// //         this.source = source;
// //     }
// // }

// package com.ijmsabc_backend.ijmsabc_java_backend.Entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "pdfs")
// public class Pdf {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, length = 255)
//     private String title;

//     @Lob
//     @Column(name = "pdf_doc", columnDefinition = "LONGBLOB", nullable = false)
//     private byte[] pdfDoc;

//     @Column(length = 50)
//     private String volume;

//     @Column(name = "issue_no", length = 50)
//     private String issueNo;

//     @Column(name = "pub_year", length = 10)
//     private String pubYear;

//     @Column(name = "doc_type", length = 100)
//     private String issueType;

//     @Column(length = 255)
//     private String author;

//     @Column(length = 255)
//     private String doi;

//     @Column(length = 255)
//     private String source;

//     public Pdf() {}

//     public Pdf(String title, byte[] pdfDoc, String volume,
//                String issueNo, String pubYear, String issueType,
//                String author, String doi, String source) {

//         this.title = title;
//         this.pdfDoc = pdfDoc;
//         this.volume = volume;
//         this.issueNo = issueNo;
//         this.pubYear = pubYear;
//         this.issueType = issueType;
//         this.author = author;
//         this.doi = doi;
//         this.source = source;
//     }

//     // Getters & Setters

//     public Long getId() {
//         return id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public byte[] getPdfDoc() {
//         return pdfDoc;
//     }

//     public void setPdfDoc(byte[] pdfDoc) {
//         this.pdfDoc = pdfDoc;
//     }

//     public String getVolume() {
//         return volume;
//     }

//     public void setVolume(String volume) {
//         this.volume = volume;
//     }

//     public String getIssueNo() {
//         return issueNo;
//     }

//     public void setIssueNo(String issueNo) {
//         this.issueNo = issueNo;
//     }

//     public String getPubYear() {
//         return pubYear;
//     }

//     public void setPubYear(String pubYear) {
//         this.pubYear = pubYear;
//     }

//     public String getIssueType() {
//         return issueType;
//     }

//     public void setIssueType(String issueType) {
//         this.issueType = issueType;
//     }

//     public String getAuthor() {
//         return author;
//     }

//     public void setAuthor(String author) {
//         this.author = author;
//     }

//     public String getDoi() {
//         return doi;
//     }

//     public void setDoi(String doi) {
//         this.doi = doi;
//     }

//     public String getSource() {
//         return source;
//     }

//     public void setSource(String source) {
//         this.source = source;
//     }
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
@Table(name = "pdfs")
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String volume;
    private String issueNo;
    private String pubYear;
    private String issueType;
    private String author;
    private String doi;
    private String source;

    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = false)
    private byte[] pdfDoc;

    public Pdf() {}

    public Pdf(String title, String volume, String issueNo,
               String pubYear, String issueType,
               String author, String doi, String source,
               byte[] pdfDoc) {

        this.title = title;
        this.volume = volume;
        this.issueNo = issueNo;
        this.pubYear = pubYear;
        this.issueType = issueType;
        this.author = author;
        this.doi = doi;
        this.source = source;
        this.pdfDoc = pdfDoc;
    }

    // Getters & Setters

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }

    public String getIssueNo() { return issueNo; }
    public void setIssueNo(String issueNo) { this.issueNo = issueNo; }

    public String getPubYear() { return pubYear; }
    public void setPubYear(String pubYear) { this.pubYear = pubYear; }

    public String getIssueType() { return issueType; }
    public void setIssueType(String issueType) { this.issueType = issueType; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getDoi() { return doi; }
    public void setDoi(String doi) { this.doi = doi; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public byte[] getPdfDoc() { return pdfDoc; }
    public void setPdfDoc(byte[] pdfDoc) { this.pdfDoc = pdfDoc; }
}
