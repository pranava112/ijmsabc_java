package com.ijmsabc_backend.ijmsabc_java_backend.Controller;


import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

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

    // @PostMapping("/upload")
    // public ResponseEntity<Pdf> uploadPdf(
    //         @RequestParam("title") String title,
    //         @RequestParam("pdf_doc") MultipartFile file,
    //         @RequestParam("volume") String volume,
    //         @RequestParam("issue") String issue,
    //         @RequestParam("year") String year,
    //         @RequestParam("type") String type
    // ) throws IOException {
    //     Pdf pdf = new Pdf(title, file.getBytes(), volume, issue, year, type);
    //     return ResponseEntity.ok(pdfService.savePdf(pdf));
    // }



    @PostMapping("/upload")
public ResponseEntity<Pdf> uploadPdf(
        @RequestParam("title") String title,
        @RequestParam("pdf_doc") MultipartFile file,
        @RequestParam("volume") String volume,
        @RequestParam("issueNo") String issueNo,
        @RequestParam("year") String year,
        @RequestParam("type") String type,
        @RequestParam("author") String author
) throws IOException {
    Pdf pdf = new Pdf(title, file.getBytes(), volume, issueNo, year, type, author);
    return ResponseEntity.ok(pdfService.savePdf(pdf));
}

@PutMapping("/{id}")
public ResponseEntity<Pdf> updatePdf(
        @PathVariable Long id,
        @RequestParam("title") String title,
        @RequestParam("volume") String volume,
        @RequestParam("issueNo") String issueNo,
        @RequestParam("year") String year,
        @RequestParam("type") String type,
        @RequestParam("author") String author,
        @RequestParam(value = "pdf_doc", required = false) MultipartFile pdfDoc
) throws IOException {
    byte[] fileBytes = null;
    if (pdfDoc != null && !pdfDoc.isEmpty()) {
        fileBytes = pdfDoc.getBytes();
    }
    Pdf updatedPdf = new Pdf(title, fileBytes, volume, issueNo, year, type,author);
    return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
}


    @GetMapping
    public List<Pdf> getAllPdfs() {
        return pdfService.getAllPdfs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pdf> getPdfById(@PathVariable Long id) {
        return pdfService.getPdfById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Pdf> updatePdf(
    //         @PathVariable Long id,
    //         @RequestParam("title") String title,
    //         @RequestParam("pdf_doc") MultipartFile file,
    //         @RequestParam("volume") String volume,
    //         @RequestParam("issue") String issue,
    //         @RequestParam("year") String year,
    //         @RequestParam("type") String type
    // ) throws IOException {
    //     Pdf updatedPdf = new Pdf(title, file.getBytes(), volume, issue, year, type);
    //     return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
    // }

//     @PutMapping("/{id}")
// public ResponseEntity<Pdf> updatePdf(
//         @PathVariable Long id,
//         @RequestParam("title") String title,
//         @RequestParam("volume") String volume,
//         @RequestParam("issue") String issue,
//         @RequestParam("year") String year,
//         @RequestParam("type") String type,
//         @RequestParam(value = "pdf_doc", required = false) MultipartFile pdfDoc
// ) throws IOException {
    
//     byte[] fileBytes = null;
//     if (pdfDoc != null && !pdfDoc.isEmpty()) {
//         fileBytes = pdfDoc.getBytes();
//     }

//     Pdf updatedPdf = new Pdf(title, fileBytes, volume, issue, year, type);
//     return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
// }

@GetMapping("/{id}/file")
public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
    return pdfService.getPdfById(id)
            .map(pdf -> ResponseEntity.ok()
                    .header("Content-Disposition", "inline; filename=\"" + pdf.getTitle() + ".pdf\"")
                    .header("Content-Type", "application/pdf")
                    .body(pdf.getPdfDoc()))
            .orElse(ResponseEntity.notFound().build());
}



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePdf(@PathVariable Long id) {
        pdfService.deletePdf(id);
        return ResponseEntity.noContent().build();
    }
}

