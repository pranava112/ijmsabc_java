

package com.ijmsabc_backend.ijmsabc_java_backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pdfs")
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "pdf_link", nullable = false)
    private String pdfLink;

    @Column(name = "volume")
    private String volume;

    @Column(name = "author")
    private String author;

    @Column(name = "issue_no")
    private String issueNo;

    // Avoid reserved keyword
    @Column(name = "pub_year")
    private String year;

    @Column(name = "doc_type")
    private String type;

    @Column(name = "source", nullable = false)
    private String source;

     @Column(name = "doi", nullable = false)
    private String doi;

    // Constructors
    public Pdf() {
    }

    public Pdf(String title, String pdfLink, String volume, String issueNo,
               String year, String type, String author, String source,String doi) {
        this.title = title;
        this.pdfLink = pdfLink;
        this.volume = volume;
        this.issueNo = issueNo;
        this.year = year;
        this.type = type;
        this.author = author;
        this.source = source;
        this.doi = doi;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPdfLink() { return pdfLink; }
    public void setPdfLink(String pdfLink) { this.pdfLink = pdfLink; }

    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIssueNo() { return issueNo; }
    public void setIssueNo(String issueNo) { this.issueNo = issueNo; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDoi() { return doi; }
    public void setDoi(String doi) { this.doi = doi; }
}
