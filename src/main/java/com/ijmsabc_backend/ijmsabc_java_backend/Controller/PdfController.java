



// // package com.ijmsabc_backend.ijmsabc_java_backend.Controller;

// // import java.net.MalformedURLException;
// // import java.nio.file.Files;
// // import java.nio.file.Path;
// // import java.nio.file.Paths;
// // import java.nio.file.StandardCopyOption;
// // import java.util.List;

// // import org.springframework.core.io.Resource;
// // import org.springframework.core.io.UrlResource;
// // import org.springframework.http.HttpHeaders;
// // import org.springframework.http.MediaType;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.util.StringUtils;
// // import org.springframework.web.bind.annotation.CrossOrigin;
// // import org.springframework.web.bind.annotation.DeleteMapping;
// // import org.springframework.web.bind.annotation.GetMapping;
// // import org.springframework.web.bind.annotation.PathVariable;
// // import org.springframework.web.bind.annotation.PostMapping;
// // import org.springframework.web.bind.annotation.PutMapping;
// // import org.springframework.web.bind.annotation.RequestMapping;
// // import org.springframework.web.bind.annotation.RequestParam;
// // import org.springframework.web.bind.annotation.RequestPart;
// // import org.springframework.web.bind.annotation.RestController;
// // import org.springframework.web.multipart.MultipartFile;

// // import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Pdf;
// // import com.ijmsabc_backend.ijmsabc_java_backend.Service.PdfService;

// // @RestController
// // @RequestMapping("/api/ijmsabc/pdfs")
// // // Allow local dev origins; replace with production origin(s) in production
// // @CrossOrigin(origins = {"https://www.ijmsabc.org", "http://localhost:5173", "http://localhost:3000"})
// // public class PdfController {

// //     private final PdfService pdfService;

// //     public PdfController(PdfService pdfService) {
// //         this.pdfService = pdfService;
// //     }

// //     // ===================== UPLOAD PDF =====================
// //     @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
// //     public ResponseEntity<Pdf> uploadPdf(
// //             @RequestParam("title") String title,
// //             @RequestPart(value = "file", required = false) MultipartFile file,
// //             @RequestParam("volume") String volume,
// //             @RequestParam("issueNo") String issueNo,
// //             @RequestParam("year") String year,
// //             @RequestParam("type") String type,
// //             @RequestParam("author") String author,
// //             @RequestParam("source") String source,
// //             @RequestParam("doi") String doi
// //         ) throws Exception {
// //         if (title == null || title.trim().isEmpty()) {
// //             return ResponseEntity.badRequest().build();
// //         }

// //         String storedPath = "";
// //         if (file != null && !file.isEmpty()) {
// //             String uploadsDir = "uploads"; // relative to working dir
// //             Path uploadPath = Paths.get(uploadsDir);
// //             if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

// //             String original = StringUtils.cleanPath(file.getOriginalFilename());
// //             String filename = System.currentTimeMillis() + "_" + original;
// //             Path target = uploadPath.resolve(filename);
// //             Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

// //             // Expose via /api/ijmsabc/pdfs/files/{filename}
// //             storedPath = "/api/ijmsabc/pdfs/files/" + filename;
// //         }

// //         Pdf pdf = new Pdf(
// //             title,
// //             storedPath,
// //             volume,
// //             issueNo,
// //             year,
// //             type,
// //             author,
// //             source,
// //             doi
// //         );

// //         return ResponseEntity.ok(pdfService.savePdf(pdf));
// //         }

// //     // ===================== UPDATE PDF =====================
// //     @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
// //     public ResponseEntity<Pdf> updatePdf(
// //             @PathVariable Long id,
// //             @RequestParam("title") String title,
// //             @RequestParam("volume") String volume,
// //             @RequestParam("issueNo") String issueNo,
// //             @RequestParam("year") String year,
// //             @RequestParam("type") String type,
// //             @RequestParam("author") String author,
// //             @RequestParam("source") String source,
// //              @RequestParam("doi") String doi,
// //              @RequestPart(value = "file", required = false) MultipartFile file
// //         ) throws Exception {

// //         Pdf updatedPdf = new Pdf();
// //         updatedPdf.setTitle(title);
// //         updatedPdf.setVolume(volume);
// //         updatedPdf.setIssueNo(issueNo);
// //         updatedPdf.setYear(year);
// //         updatedPdf.setType(type);
// //         updatedPdf.setAuthor(author);
// //         updatedPdf.setSource(source);
// //         updatedPdf.setDoi(doi);

// //         if (file != null && !file.isEmpty()) {
// //             String uploadsDir = "uploads";
// //             Path uploadPath = Paths.get(uploadsDir);
// //             if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
// //             String original = StringUtils.cleanPath(file.getOriginalFilename());
// //             String filename = System.currentTimeMillis() + "_" + original;
// //             Path target = uploadPath.resolve(filename);
// //             Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
// //             updatedPdf.setPdfFile("/api/ijmsabc/pdfs/files/" + filename);
// //         }

// //         return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
// //     }

// //     // ===================== GET ALL =====================
// //     @GetMapping
// //     public List<Pdf> getAllPdfs() {
// //         return pdfService.getAllPdfs();
// //     }

// //     // ===================== GET BY ID =====================
// //     @GetMapping("/{id}")
// //     public ResponseEntity<Pdf> getPdfById(@PathVariable Long id) {
// //         return pdfService.getPdfById(id)
// //                 .map(ResponseEntity::ok)
// //                 .orElse(ResponseEntity.notFound().build());
// //     }

// //     // ===================== SERVE FILE =====================
// //     @GetMapping("/files/{filename:.+}")
// //     public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
// //         try {
// //             Path file = Paths.get("uploads").resolve(filename).normalize();
// //             Resource resource = new UrlResource(file.toUri());
// //             if (!resource.exists()) return ResponseEntity.notFound().build();

// //             String contentType = "application/pdf";
// //             return ResponseEntity.ok()
// //                     .contentType(MediaType.parseMediaType(contentType))
// //                     .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
// //                     .body(resource);
// //         } catch (MalformedURLException e) {
// //             return ResponseEntity.internalServerError().build();
// //         }
// //     }



// //     // ===================== DELETE =====================
// //     @DeleteMapping("/{id}")
// //     public ResponseEntity<Void> deletePdf(@PathVariable Long id) {
// //         pdfService.deletePdf(id);
// //         return ResponseEntity.noContent().build();
// //     }
// // }



// package com.ijmsabc_backend.ijmsabc_java_backend.Controller;


// import java.io.IOException;
// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Pdf;
// import com.ijmsabc_backend.ijmsabc_java_backend.Service.PdfService;


// @RestController
// @RequestMapping("/api/ijmsabc/pdfs")
// @CrossOrigin(origins = {
//     "https://www.ijmsabc.org", 
//     "http://localhost:5173"    
// })

// public class PdfController {



//     private final PdfService pdfService;

//     public PdfController(PdfService pdfService) {
//         this.pdfService = pdfService;
//     }

//     // @PostMapping("/upload")
//     // public ResponseEntity<Pdf> uploadPdf(
//     //         @RequestParam("title") String title,
//     //         @RequestParam("pdf_doc") MultipartFile file,
//     //         @RequestParam("volume") String volume,
//     //         @RequestParam("issue") String issue,
//     //         @RequestParam("year") String year,
//     //         @RequestParam("type") String type
//     // ) throws IOException {
//     //     Pdf pdf = new Pdf(title, file.getBytes(), volume, issue, year, type);
//     //     return ResponseEntity.ok(pdfService.savePdf(pdf));
//     // }



//     @PostMapping("/upload")
// public ResponseEntity<Pdf> uploadPdf(
//         @RequestParam("title") String title,
//         @RequestParam("pdf_doc") MultipartFile file,
//         @RequestParam("volume") String volume,
//         @RequestParam("issueNo") String issueNo,
//         @RequestParam("year") String year,
//         @RequestParam("type") String type,
//          @RequestParam("doi") String doi,
//             @RequestParam("source") String source,
//         @RequestParam("author") String author
// ) throws IOException {
//     Pdf pdf = new Pdf(title, file.getBytes(), volume, issueNo, year, type, author,source,doi);
//     return ResponseEntity.ok(pdfService.savePdf(pdf));
// }

// @PutMapping("/{id}")
// public ResponseEntity<Pdf> updatePdf(
//         @PathVariable Long id,
//         @RequestParam("title") String title,
//         @RequestParam("volume") String volume,
//         @RequestParam("issueNo") String issueNo,
//         @RequestParam("year") String year,
//         @RequestParam("type") String type,
//          @RequestParam("doi") String doi,
//             @RequestParam("source") String source,
//         @RequestParam("author") String author,
//         @RequestParam(value = "pdf_doc", required = false) MultipartFile pdfDoc
// ) throws IOException {
//     byte[] fileBytes = null;
//     if (pdfDoc != null && !pdfDoc.isEmpty()) {
//         fileBytes = pdfDoc.getBytes();
//     }
//     Pdf updatedPdf = new Pdf(title, fileBytes, volume, issueNo, year, type,author,doi,source);
//     return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
// }


//     @GetMapping
//     public List<Pdf> getAllPdfs() {
//         return pdfService.getAllPdfs();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Pdf> getPdfById(@PathVariable Long id) {
//         return pdfService.getPdfById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     // @PutMapping("/{id}")
//     // public ResponseEntity<Pdf> updatePdf(
//     //         @PathVariable Long id,
//     //         @RequestParam("title") String title,
//     //         @RequestParam("pdf_doc") MultipartFile file,
//     //         @RequestParam("volume") String volume,
//     //         @RequestParam("issue") String issue,
//     //         @RequestParam("year") String year,
//     //         @RequestParam("type") String type
//     // ) throws IOException {
//     //     Pdf updatedPdf = new Pdf(title, file.getBytes(), volume, issue, year, type);
//     //     return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
//     // }

// //     @PutMapping("/{id}")
// // public ResponseEntity<Pdf> updatePdf(
// //         @PathVariable Long id,
// //         @RequestParam("title") String title,
// //         @RequestParam("volume") String volume,
// //         @RequestParam("issue") String issue,
// //         @RequestParam("year") String year,
// //         @RequestParam("type") String type,
// //         @RequestParam(value = "pdf_doc", required = false) MultipartFile pdfDoc
// // ) throws IOException {
    
// //     byte[] fileBytes = null;
// //     if (pdfDoc != null && !pdfDoc.isEmpty()) {
// //         fileBytes = pdfDoc.getBytes();
// //     }

// //     Pdf updatedPdf = new Pdf(title, fileBytes, volume, issue, year, type);
// //     return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
// // }

// @GetMapping("/{id}/file")
// public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
//     return pdfService.getPdfById(id)
//             .map(pdf -> ResponseEntity.ok()
//                     .header("Content-Disposition", "inline; filename=\"" + pdf.getTitle() + ".pdf\"")
//                     .header("Content-Type", "application/pdf")
//                     .body(pdf.getPdfDoc()))
//             .orElse(ResponseEntity.notFound().build());
// }



//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deletePdf(@PathVariable Long id) {
//         pdfService.deletePdf(id);
//         return ResponseEntity.noContent().build();
//     }
// }



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

    // ✅ Upload PDF
    @PostMapping("/upload")
    public ResponseEntity<Pdf> uploadPdf(
            @RequestParam("title") String title,
            @RequestParam("pdf_doc") MultipartFile file,
            @RequestParam("volume") String volume,
            @RequestParam("issueNo") String issueNo,
            @RequestParam("year") String year,
            @RequestParam("type") String type,
            @RequestParam("author") String author,
            @RequestParam("doi") String doi,
            @RequestParam("source") String source
    ) throws IOException {

        Pdf pdf = new Pdf(
                title,
                file.getBytes(),
                volume,
                issueNo,
                year,
                type,
                author,
                doi,
                source
        );

        return ResponseEntity.ok(pdfService.savePdf(pdf));
    }

    // ✅ Get All PDFs
    @GetMapping
    public List<Pdf> getAllPdfs() {
        return pdfService.getAllPdfs();
    }

    // ✅ Get PDF Metadata By ID
    @GetMapping("/{id}")
    public ResponseEntity<Pdf> getPdfById(@PathVariable Long id) {
        return pdfService.getPdfById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Download PDF File
    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {

        return pdfService.getPdfById(id)
                .map(pdf -> ResponseEntity.ok()
                        .header("Content-Disposition",
                                "inline; filename=\"" + pdf.getTitle() + ".pdf\"")
                        .header("Content-Type", "application/pdf")
                        .body(pdf.getPdfDoc()))
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update PDF (File Optional)
    @PutMapping("/{id}")
    public ResponseEntity<Pdf> updatePdf(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("volume") String volume,
            @RequestParam("issueNo") String issueNo,
            @RequestParam("year") String year,
            @RequestParam("type") String type,
            @RequestParam("author") String author,
            @RequestParam("doi") String doi,
            @RequestParam("source") String source,
            @RequestParam(value = "pdf_doc", required = false) MultipartFile pdfDoc
    ) throws IOException {

        byte[] fileBytes = null;

        if (pdfDoc != null && !pdfDoc.isEmpty()) {
            fileBytes = pdfDoc.getBytes();
        }

        Pdf updatedPdf = new Pdf(
                title,
                fileBytes,
                volume,
                issueNo,
                year,
                type,
                author,
                doi,
                source
        );

        return ResponseEntity.ok(pdfService.updatePdf(id, updatedPdf));
    }

    // ✅ Delete PDF
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePdf(@PathVariable Long id) {
        pdfService.deletePdf(id);
        return ResponseEntity.noContent().build();
    }
}
