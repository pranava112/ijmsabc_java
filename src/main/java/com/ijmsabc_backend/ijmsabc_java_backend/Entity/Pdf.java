package com.ijmsabc_backend.ijmsabc_java_backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pdfs")
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(name = "pdf_doc", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] pdfDoc;

    private String volume;

    @Column(name = "issue_no")
    private String issueNo;

    @Column(name = "pub_year")
    private String year;

    @Column(name = "doc_type")
    private String type;

    private String author;

    private String doi;

    private String source;

    // ✅ Default Constructor
    public Pdf() {}

    // ✅ Correct Constructor
    public Pdf(String title, byte[] pdfDoc, String volume, String issueNo,
               String year, String type, String author, String doi, String source) {

        this.title = title;
        this.pdfDoc = pdfDoc;
        this.volume = volume;
        this.issueNo = issueNo;
        this.year = year;
        this.type = type;
        this.author = author;
        this.doi = doi;
        this.source = source;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public byte[] getPdfDoc() { return pdfDoc; }
    public void setPdfDoc(byte[] pdfDoc) { this.pdfDoc = pdfDoc; }

    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }

    public String getIssueNo() { return issueNo; }
    public void setIssueNo(String issueNo) { this.issueNo = issueNo; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getDoi() { return doi; }
    public void setDoi(String doi) { this.doi = doi; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
