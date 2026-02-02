

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
@CrossOrigin(origins = "http://localhost:5173")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    // ✅ Upload PDF
    @PostMapping("/upload")
    public ResponseEntity<?> uploadPdf(
            @RequestParam String title,
            @RequestParam String volume,
            @RequestParam String issueNo,
            @RequestParam String pubYear,
            @RequestParam String issueType,
            @RequestParam String author,
            @RequestParam String doi,
            @RequestParam String source,
            @RequestParam MultipartFile pdfDoc
    ) {
        try {
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

            return ResponseEntity.ok(pdfService.savePdf(pdf));

        } catch (IOException e) {
            return ResponseEntity.status(500).body("File Upload Failed");
        }
    }

    // ✅ Get All PDFs
    @GetMapping
    public List<Pdf> getAllPdfs() {
        return pdfService.getAllPdfs();
    }

    // ✅ View / Download PDF by ID (Browser Supported)
@GetMapping("/download/{id}")
public ResponseEntity<byte[]> viewPdf(@PathVariable Long id) {

    Pdf pdf = pdfService.getPdfById(id);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "inline; filename=\"" + pdf.getTitle() + ".pdf\"")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdf.getPdfDoc());
}


    // ✅ Download PDF File
    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {

        Pdf pdf = pdfService.getPdfById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + pdf.getTitle() + ".pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.getPdfDoc());
    }

    // ✅ UPDATE PDF (🔥 NEW FIXED EDIT API)
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePdf(
            @PathVariable Long id,
            @RequestParam String volume,
            @RequestParam String issueNo,
            @RequestParam String pubYear,
            @RequestParam String issueType,
            @RequestParam String author,
            @RequestParam String doi,
            @RequestParam String source,
            @RequestParam(required = false) MultipartFile pdfDoc
    ) {
        try {
            Pdf updatedPdf = pdfService.updatePdf(id,
                    volume,
                    issueNo,
                    pubYear,
                    issueType,
                    author,
                    doi,
                    source,
                    pdfDoc
            );

            return ResponseEntity.ok(updatedPdf);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Update Failed: " + e.getMessage());
        }
    }

    // ✅ Delete PDF
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePdf(@PathVariable Long id) {
        pdfService.deletePdf(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
