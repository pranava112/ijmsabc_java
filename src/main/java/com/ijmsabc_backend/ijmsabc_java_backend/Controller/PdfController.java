package com.ijmsabc_backend.ijmsabc_java_backend.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Pdf;
import com.ijmsabc_backend.ijmsabc_java_backend.Service.PdfService;

@RestController
@RequestMapping("/api/ijmsabc/pdfs")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://www.ijmsabc.org",
        "https://api.ijmsabc.org"
})

public class PdfController {

    private final PdfService service;

    public PdfController(PdfService service) {
        this.service = service;
    }

    // ✅ Upload PDF
    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPdf(
            @RequestParam("title") String title,
            @RequestParam("volume") String volume,
            @RequestParam("issue_no") String issueNo,
            @RequestParam("pub_year") String pubYear,
            @RequestParam("issue_type") String issueType,
            @RequestParam("author") String author,
            @RequestParam("doi") String doi,
            @RequestParam("source") String source,
            @RequestParam("pdf_doc") MultipartFile pdfDoc
    ) throws IOException {

        // ✅ File Extension Validation
        String filename = pdfDoc.getOriginalFilename();

        if (filename == null || !filename.toLowerCase().endsWith(".pdf")) {
            return ResponseEntity.badRequest().body("Only PDF files allowed!");
        }

        Pdf pdf = new Pdf(
                title,
                volume,
                issueNo,
                pubYear,
                issueType,
                author,
                doi,
                source,
                pdfDoc.getBytes()
        );

        return ResponseEntity.ok(service.save(pdf));
    }

    // ✅ Get All PDFs
    @GetMapping
    public List<Pdf> getAll() {
        return service.getAll();
    }

    // ✅ View PDF
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> view(@PathVariable Long id) {

        Pdf pdf = service.getById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition",
                        "inline; filename=" + pdf.getTitle() + ".pdf")
                .body(pdf.getPdfDoc());
    }

    // ✅ Delete PDF
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }


    @PutMapping(value="/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> updatePdf(
        @PathVariable Long id,
        @RequestParam("title") String title,
        @RequestParam("volume") String volume,
        @RequestParam("issue_no") String issueNo,
        @RequestParam("pub_year") String pubYear,
        @RequestParam("issue_type") String issueType,
        @RequestParam("author") String author,
        @RequestParam("doi") String doi,
        @RequestParam("source") String source,
        @RequestParam(value="pdf_doc", required=false) MultipartFile pdfDoc
) throws IOException {

    Pdf existing = service.getById(id);

    existing.setTitle(title);
    existing.setVolume(volume);
    existing.setIssueNo(issueNo);
    existing.setPubYear(pubYear);
    existing.setIssueType(issueType);
    existing.setAuthor(author);
    existing.setDoi(doi);
    existing.setSource(source);

    // ✅ Replace file only if uploaded
    if (pdfDoc != null && !pdfDoc.isEmpty()) {
        existing.setPdfDoc(pdfDoc.getBytes());
    }

    return ResponseEntity.ok(service.save(existing));
}

}
