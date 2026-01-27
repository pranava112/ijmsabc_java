



package com.ijmsabc_backend.ijmsabc_java_backend.Controller;

import java.util.List;

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

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Pdf;
import com.ijmsabc_backend.ijmsabc_java_backend.Service.PdfService;

@RestController
@RequestMapping("/api/ijmsabc/pdfs")
@CrossOrigin(origins = {
        "https://www.ijmsabc.org",
        "http://localhost:5173"
})
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    // ===================== UPLOAD PDF =====================
    @PostMapping("/upload")
    public ResponseEntity<Pdf> uploadPdf(
            @RequestParam("title") String title,
            @RequestParam("pdf_link") String pdfLink,
            @RequestParam("volume") String volume,
            @RequestParam("issueNo") String issueNo,
            @RequestParam("year") String year,
            @RequestParam("type") String type,
            @RequestParam("author") String author,
            @RequestParam("source") String source,
            @RequestParam("doi") String doi
    ) {

        Pdf pdf = new Pdf(
                title,
                pdfLink,
                volume,
                issueNo,
                year,
                type,
                author,
                source,
                doi
        );

        return ResponseEntity.ok(pdfService.savePdf(pdf));
    }

    // ===================== UPDATE PDF =====================
    @PutMapping("/{id}")
    public ResponseEntity<Pdf> updatePdf(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("volume") String volume,
            @RequestParam("issueNo") String issueNo,
            @RequestParam("year") String year,
            @RequestParam("type") String type,
            @RequestParam("author") String author,
            @RequestParam("source") String source,
             @RequestParam("doi") String doi,
            @RequestParam(value = "pdf_link", required = false) String pdfLink
    ) {

        Pdf updatedPdf = new Pdf();
        updatedPdf.setTitle(title);
        updatedPdf.setVolume(volume);
        updatedPdf.setIssueNo(issueNo);
        updatedPdf.setYear(year);
        updatedPdf.setType(type);
        updatedPdf.setAuthor(author);
        updatedPdf.setSource(source);
        updatedPdf.setDoi(doi);

        if (pdfLink != null && !pdfLink.isEmpty()) {
            updatedPdf.setPdfLink(pdfLink);
        }

        return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
    }

    // ===================== GET ALL =====================
    @GetMapping
    public List<Pdf> getAllPdfs() {
        return pdfService.getAllPdfs();
    }

    // ===================== GET BY ID =====================
    @GetMapping("/{id}")
    public ResponseEntity<Pdf> getPdfById(@PathVariable Long id) {
        return pdfService.getPdfById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    // ===================== DELETE =====================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePdf(@PathVariable Long id) {
        pdfService.deletePdf(id);
        return ResponseEntity.noContent().build();
    }
}
