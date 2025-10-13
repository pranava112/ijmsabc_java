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

    @Lob
    @Column(name = "pdf_doc", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] pdfDoc;

    @Column(name = "volume")
    private String volume;

      @Column(name = "author")
    private String author;
 

    @Column(name = "issueNo")
private String issueNo;


    // ✅ Avoid reserved keyword conflicts by renaming
    @Column(name = "pub_year")
    private String year;

    @Column(name = "doc_type")
    private String type;

    // Constructors
    public Pdf() {}

    public Pdf(String title, byte[] pdfDoc, String volume, String issueNo, String year, String type,String author) {
        this.title = title;
        this.pdfDoc = pdfDoc;
        this.volume = volume;
        this.issueNo = issueNo;
        this.year = year;
        this.type = type;
        this.author=author;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public byte[] getPdfDoc() { return pdfDoc; }
    public void setPdfDoc(byte[] pdfDoc) { this.pdfDoc = pdfDoc; }

    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
   

    public String getIssueNo() { return issueNo; }
public void setIssueNo(String issueNo) { this.issueNo = issueNo; }


    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getType() { return type; }
    public void setType(String type) 
    { 
        this.type = type;
    }
}