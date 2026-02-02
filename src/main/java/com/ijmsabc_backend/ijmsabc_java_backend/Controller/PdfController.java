package com.ijmsabc_backend.ijmsabc_java_backend.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
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

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    // =====================================================
    // ✅ 1. Upload PDF
    // =====================================================
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPdf(
            @RequestParam("title") String title,
            @RequestParam("volume") String volume,
            @RequestParam("issueNo") String issueNo,
            @RequestParam("pubYear") String pubYear,
            @RequestParam("issueType") String issueType,
            @RequestParam("author") String author,
            @RequestParam("doi") String doi,
            @RequestParam("source") String source,
            @RequestParam("pdf_doc") MultipartFile pdfDoc
    ) {
        // try {
        //     if (pdfDoc.isEmpty()) return ResponseEntity.badRequest().body("PDF File is required!");
        //     if (!pdfDoc.getContentType().equals("application/pdf"))
        //         return ResponseEntity.badRequest().body("Only PDF files are allowed!");

        //     Pdf pdf = new Pdf(title, volume, issueNo, pubYear, issueType, author, doi, source, pdfDoc.getBytes());
        //     Pdf savedPdf = pdfService.savePdf(pdf);

        //     return ResponseEntity.ok(savedPdf);

        // } catch (IOException e) {
        //     return ResponseEntity.status(500).body("File Upload Failed: " + e.getMessage());
        // } catch (Exception e) {
        //     return ResponseEntity.status(500).body("Unexpected Error: " + e.getMessage());
        // }

        try {

    // ✅ Check file exists
    if (pdfDoc == null || pdfDoc.isEmpty()) {
        return ResponseEntity.badRequest().body("PDF File is required!");
    }

    // ✅ Safe Content-Type Check
    String contentType = pdfDoc.getContentType();

    if (contentType == null || !contentType.equalsIgnoreCase("application/pdf")) {
        return ResponseEntity.badRequest().body("Only PDF files are allowed!");
    }

    // ✅ Save PDF
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

    Pdf savedPdf = pdfService.savePdf(pdf);

    return ResponseEntity.ok(savedPdf);

} catch (IOException e) {
    return ResponseEntity.status(500).body("File Upload Failed: " + e.getMessage());

} catch (Exception e) {
    return ResponseEntity.status(500).body("Unexpected Error: " + e.getMessage());
}


    }

    // =====================================================
    // ✅ 2. Get All PDFs
    // =====================================================
    @GetMapping
    public ResponseEntity<List<Pdf>> getAllPdfs() {
        return ResponseEntity.ok(pdfService.getAllPdfs());
    }

    // =====================================================
    // ✅ 3. Get PDF by ID
    // =====================================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getPdfById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pdfService.getPdfById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("PDF Not Found with ID: " + id);
        }
    }

    // =====================================================
    // ✅ 4. VIEW PDF in Browser (INLINE)
    // =====================================================
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewPdf(@PathVariable Long id) {
        Pdf pdf = pdfService.getPdfById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + pdf.getTitle() + ".pdf\"") // ✅ INLINE view
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.getPdfDoc());
    }

    // =====================================================
    // ✅ 5. Update PDF Metadata + Optional File
    // =====================================================
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePdf(
            @PathVariable Long id,
            @RequestParam("volume") String volume,
            @RequestParam("issueNo") String issueNo,
            @RequestParam("pubYear") String pubYear,
            @RequestParam("issueType") String issueType,
            @RequestParam("author") String author,
            @RequestParam("doi") String doi,
            @RequestParam("source") String source,
            @RequestParam(value = "pdf_doc", required = false) MultipartFile pdfDoc
    ) {
        try {
            Pdf updated = pdfService.updatePdf(id, volume, issueNo, pubYear, issueType, author, doi, source, pdfDoc);
            return ResponseEntity.ok(updated);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Update Failed: " + e.getMessage());
        }
    }

    // =====================================================
    // ✅ 6. Delete PDF
    // =====================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePdf(@PathVariable Long id) {
        try {
            pdfService.deletePdf(id);
            return ResponseEntity.ok("PDF Deleted Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Delete Failed: " + e.getMessage());
        }
    }
}
