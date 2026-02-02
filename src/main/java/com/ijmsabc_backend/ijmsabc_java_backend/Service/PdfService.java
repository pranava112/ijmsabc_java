

package com.ijmsabc_backend.ijmsabc_java_backend.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Pdf;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.PdfRepository;

@Service
public class PdfService {

    private final PdfRepository pdfRepository;

    public PdfService(PdfRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

    public Pdf savePdf(Pdf pdf) {
        return pdfRepository.save(pdf);
    }

    public List<Pdf> getAllPdfs() {
        return pdfRepository.findAll();
    }

    public Pdf getPdfById(Long id) {
        return pdfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PDF Not Found"));
    }

    public void deletePdf(Long id) {
        pdfRepository.deleteById(id);
    }

    // ✅ UPDATE LOGIC (🔥 Needed for Edit Form)
    public Pdf updatePdf(Long id,
                         String volume,
                         String issueNo,
                         String pubYear,
                         String issueType,
                         String author,
                         String doi,
                         String source,
                         MultipartFile pdfDoc) throws IOException {

        Pdf existingPdf = getPdfById(id);

        existingPdf.setVolume(volume);
        existingPdf.setIssueNo(issueNo);
        existingPdf.setPubYear(pubYear);
        existingPdf.setIssueType(issueType);
        existingPdf.setAuthor(author);
        existingPdf.setDoi(doi);
        existingPdf.setSource(source);

        // ✅ Replace PDF only if new file uploaded
        if (pdfDoc != null && !pdfDoc.isEmpty()) {
            existingPdf.setPdfDoc(pdfDoc.getBytes());
        }

        return pdfRepository.save(existingPdf);
    }
}
