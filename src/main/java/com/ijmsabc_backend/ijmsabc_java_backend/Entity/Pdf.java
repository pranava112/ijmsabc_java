
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

    @Column(nullable=false)
    private String title;

    private String volume;

    @Column(name="issue_no")
    private String issueNo;

    @Column(name="pub_year")
    private String pubYear;

    @Column(name="issue_type")
    private String issueType;

    @Column(name="author")
    private String author;

    @Column(nullable=false)
    private String doi;

    @Column(nullable=false)
    private String source;

    @Lob
    @Column(name="pdf_doc", columnDefinition="LONGBLOB", nullable=false)
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
