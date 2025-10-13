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
// @CrossOrigin(origins = {
//     "https://www.ijmsabc.org", 
//     "http://localhost:5173"    
// })

// public class ManuscriptController {
    
//     private final ManuscriptService manuscriptService;

//     public ManuscriptController(ManuscriptService manuscriptService) {
//         this.manuscriptService = manuscriptService;
//     }

    

//     // ✅ Upload Manuscript
//     @PostMapping("/upload")
//     public ResponseEntity<Manuscript> uploadManuscript(
//          @RequestParam("name") String name,
//          @RequestParam("email") String email,
//          @RequestParam("title") String title,
//            @RequestParam("phone") String phone,
//          @RequestParam("abst") String abst,
//          @RequestParam("kwords") String kwords,
//          @RequestParam("pdf_doc") MultipartFile pdfDoc
//     ) throws IOException {
//         Manuscript manuscript = new Manuscript(name, email, phone, title, abst, kwords, pdfDoc.getBytes());
//         return ResponseEntity.ok(manuscriptService.saveManuscript(manuscript));
//     }

//     // ✅ Get All
//     @GetMapping
//     public List<Manuscript> getAllManuscript() {
//         return manuscriptService.getAllManuscripts();
//     }

//     // ✅ Get by ID
//     @GetMapping("/{id}")
//     public ResponseEntity<Manuscript> getManuscriptById(@PathVariable Long id){
//         return manuscriptService.getManuscriptById(id)
//                 .map(ResponseEntity::ok)
//                 .orElse(ResponseEntity.notFound().build());
//     }

//     // ✅ Update Manuscript
//     @PutMapping("/{id}")
//     public ResponseEntity<Manuscript> updateManuscript(
//          @PathVariable Long id,
//          @RequestParam("name") String name,
//          @RequestParam("email") String email,
//          @RequestParam("title") String title,
//            @RequestParam("phone") String phone,
//          @RequestParam("abst") String abst,
//          @RequestParam("kwords") String kwords,
//          @RequestParam(value="pdf_doc", required=false) MultipartFile pdfDoc
//     ) throws IOException {
//         byte[] fileBytes = null;
//         if (pdfDoc != null && !pdfDoc.isEmpty()) {
//             fileBytes = pdfDoc.getBytes();
//         }
//         Manuscript updateManuscript = new Manuscript(name, email, phone, title, abst, kwords, fileBytes);
//         return ResponseEntity.ok(manuscriptService.updateManuscript(id, updateManuscript));
//     }

//     // ✅ Download Manuscript PDF
//     // @GetMapping("/{id}/file")
//     // public ResponseEntity<byte[]> downloadManuscript(@PathVariable Long id) {
//     //     return manuscriptService.getManuscriptById(id)
//     //             .map(manuscript -> ResponseEntity.ok()
//     //                     .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + manuscript.getTitle() + ".pdf\"")
//     //                     .contentType(MediaType.APPLICATION_PDF)
//     //                     .body(manuscript.getPdfDoc()))
//     //             .orElse(ResponseEntity.notFound().build());
//     // }


//  @GetMapping("/{id}/file")
// public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
//     return manuscriptService.getManuscriptById(id)
//             .map(manuscript -> {
//                 // Determine MIME type based on file extension
//                 String filename = manuscript.getTitle();
//                 byte[] fileBytes = manuscript.getPdfDoc(); // byte[] of the Word doc from DB
//                 String extension = "docx"; // or you can detect dynamically if you store extension
//                 String contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                
//                 if (filename.endsWith(".doc")) {
//                     contentType = "application/msword";
//                 }

//                 return ResponseEntity.ok()
//                         .header(HttpHeaders.CONTENT_DISPOSITION,
//                                 "inline; filename=\"" + filename + "." + extension + "\"")
//                         .contentType(MediaType.parseMediaType(contentType))
//                         .body(fileBytes);
//             })
//             .orElse(ResponseEntity.notFound().build());
// }



//     // ✅ Delete Manuscript
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteManuscript(@PathVariable Long id){
//         manuscriptService.deleteManuscript(id);
//         return ResponseEntity.noContent().build();
//     }
// }

///////////////////////////////////////////////////////////////////////////////////////////////
/// 
/// 
// /// 
// package com.ijmsabc_backend.ijmsabc_java_backend.Controller;

// import java.io.IOException;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Manuscript;
// import com.ijmsabc_backend.ijmsabc_java_backend.Repository.ManuscriptRepository;

// @RestController
// @RequestMapping("/api/ijmsabc/manuscript")
// @CrossOrigin(origins = {"https://www.ijmsabc.org", "http://localhost:5173"})
// public class ManuscriptController {

//     private final ManuscriptRepository manuscriptRepository;

//     public ManuscriptController(ManuscriptRepository manuscriptRepository) {
//         this.manuscriptRepository = manuscriptRepository;
//     }

//     @PostMapping("/upload")
//     public ResponseEntity<Manuscript> uploadManuscript(
//             @RequestParam("name") String name,
//             @RequestParam("email") String email,
//             @RequestParam("phone") String phone,
//             @RequestParam("title") String title,
//             @RequestParam("abst") String abst,
//             @RequestParam("kwords") String kwords,
//             @RequestParam("pdf_doc") MultipartFile file
//     ) throws IOException {

//         Manuscript manuscript = new Manuscript(
//                 name, email, phone, title, abst, kwords, file.getBytes()
//         );

//         Manuscript saved = manuscriptRepository.save(manuscript);
//         return ResponseEntity.ok(saved);
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
import org.springframework.web.bind.annotation.PutMapping;
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

    // ✅ Upload Manuscript
    @PostMapping("/upload")
    public ResponseEntity<Manuscript> uploadManuscript(
         @RequestParam("name") String name,
         @RequestParam("email") String email,
         @RequestParam("phone") String phone,
         @RequestParam("title") String title,
         @RequestParam("abst") String abst,
         @RequestParam("kwords") String kwords,
         @RequestParam("pdf_doc") MultipartFile pdfDoc
    ) throws IOException {
        Manuscript manuscript = new Manuscript(name, email, phone, title, abst, kwords, pdfDoc.getBytes());
        return ResponseEntity.ok(manuscriptService.saveManuscript(manuscript));
    }

    // ✅ Get All Manuscripts
    @GetMapping
    public List<Manuscript> getAllManuscripts() {
        return manuscriptService.getAllManuscripts();
    }

    // ✅ Get Manuscript by ID
    @GetMapping("/{id}")
    public ResponseEntity<Manuscript> getManuscriptById(@PathVariable Long id){
        return manuscriptService.getManuscriptById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update Manuscript
    @PutMapping("/{id}")
    public ResponseEntity<Manuscript> updateManuscript(
         @PathVariable Long id,
         @RequestParam("name") String name,
         @RequestParam("email") String email,
         @RequestParam("phone") String phone,
         @RequestParam("title") String title,
         @RequestParam("abst") String abst,
         @RequestParam("kwords") String kwords,
         @RequestParam(value="pdf_doc", required=false) MultipartFile pdfDoc
    ) throws IOException {
        byte[] fileBytes = null;
        if (pdfDoc != null && !pdfDoc.isEmpty()) {
            fileBytes = pdfDoc.getBytes();
        }
        Manuscript updatedManuscript = new Manuscript(name, email, phone, title, abst, kwords, fileBytes);
        return ResponseEntity.ok(manuscriptService.updateManuscript(id, updatedManuscript));
    }

    // ✅ Download Manuscript file (Word or PDF)
    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        return manuscriptService.getManuscriptById(id)
                .map(manuscript -> {
                    String filename = manuscript.getTitle();
                    byte[] fileBytes = manuscript.getPdfDoc();
                    String extension = "docx"; // default
                    String contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

                    if(filename.endsWith(".doc")) {
                        contentType = "application/msword";
                        extension = "doc";
                    } else if(filename.endsWith(".pdf")) {
                        contentType = "application/pdf";
                        extension = "pdf";
                    }

                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION,
                                    "inline; filename=\"" + filename + "." + extension + "\"")
                            .contentType(MediaType.parseMediaType(contentType))
                            .body(fileBytes);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete Manuscript
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManuscript(@PathVariable Long id){
        manuscriptService.deleteManuscript(id);
        return ResponseEntity.noContent().build();
    }
}
