
// package com.ijmsabc_backend.ijmsabc_java_backend.Controller;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
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

// import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Manuscript;
// import com.ijmsabc_backend.ijmsabc_java_backend.Service.ManuscriptService;

// @RestController
// @RequestMapping("/api/ijmsabc/manuscript")
// @CrossOrigin(origins = {"https://www.ijmsabc.org", "http://localhost:5173"})
// public class ManuscriptController {

//     private final ManuscriptService manuscriptService;

//     public ManuscriptController(ManuscriptService manuscriptService) {
//         this.manuscriptService = manuscriptService;
//     }

//     // ✅ Upload Manuscript + send confirmation email
//     @PostMapping("/upload")
//     public ResponseEntity<Manuscript> uploadManuscript(
//          @RequestParam("name") String name,
//          @RequestParam("email") String email,
//          @RequestParam("phone") String phone,
//          @RequestParam("title") String title,
//          @RequestParam("abst") String abst,
//          @RequestParam("kwords") String kwords,
//          @RequestParam("pdf_doc") MultipartFile pdfDoc
//     ) throws IOException {
//         Manuscript manuscript = new Manuscript(name, email, phone, title, abst, kwords, pdfDoc.getBytes());
//         Manuscript saved = manuscriptService.saveManuscript(manuscript);
//         return ResponseEntity.ok(saved);
//     }

//     @GetMapping
//     public List<Manuscript> getAllManuscripts() {
//         return manuscriptService.getAllManuscripts();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Manuscript> getManuscriptById(@PathVariable Long id){
//         return manuscriptService.getManuscriptById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Manuscript> updateManuscript(
//          @PathVariable Long id,
//          @RequestParam("name") String name,
//          @RequestParam("email") String email,
//          @RequestParam("phone") String phone,
//          @RequestParam("title") String title,
//          @RequestParam("abst") String abst,
//          @RequestParam("kwords") String kwords,
//          @RequestParam(value="pdf_doc", required=false) MultipartFile pdfDoc
//     ) throws IOException {
//         byte[] fileBytes = (pdfDoc != null && !pdfDoc.isEmpty()) ? pdfDoc.getBytes() : null;
//         Manuscript updatedManuscript = new Manuscript(name, email, phone, title, abst, kwords, fileBytes);
//         return ResponseEntity.ok(manuscriptService.updateManuscript(id, updatedManuscript));
//     }

//     @GetMapping("/{id}/file")
//     public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
//         return manuscriptService.getManuscriptById(id)
//                 .map(manuscript -> {
//                     String filename = manuscript.getTitle() + ".pdf";
//                     return ResponseEntity.ok()
//                             .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
//                             .contentType(MediaType.APPLICATION_PDF)
//                             .body(manuscript.getPdfDoc());
//                 })
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteManuscript(@PathVariable Long id){
//         manuscriptService.deleteManuscript(id);
//         return ResponseEntity.noContent().build();
//     }
// }



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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Manuscript;
import com.ijmsabc_backend.ijmsabc_java_backend.Service.ManuscriptService;

@RestController
@RequestMapping("/api/ijmsabc/manuscript")
@CrossOrigin(origins = {"https://www.ijmsabc.org", "http://localhost:5173"})
public class ManuscriptController {

    private final ManuscriptService manuscriptService;

    public ManuscriptController(ManuscriptService manuscriptService) {
        this.manuscriptService = manuscriptService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Manuscript> uploadManuscript(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("title") String title,
            @RequestParam("abst") String abst,
            @RequestParam("kwords") String kwords,
            @RequestParam("source") String source,//
            @RequestParam("pdf_doc") MultipartFile pdfDoc
    ) throws IOException {
        Manuscript manuscript = new Manuscript(name, email, phone, title, abst, kwords, pdfDoc.getBytes(),source);
        Manuscript saved = manuscriptService.saveManuscript(manuscript);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Manuscript> getAllManuscripts() {
        return manuscriptService.getAllManuscripts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manuscript> getManuscriptById(@PathVariable Long id) {
        return manuscriptService.getManuscriptById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        return manuscriptService.getManuscriptById(id)
                .map(manuscript -> {
                    String filename = manuscript.getTitle() + ".pdf";
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(manuscript.getPdfDoc());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManuscript(@PathVariable Long id) {
        manuscriptService.deleteManuscript(id);
        return ResponseEntity.noContent().build();
    }
}