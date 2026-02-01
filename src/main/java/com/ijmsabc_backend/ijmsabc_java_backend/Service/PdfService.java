package com.ijmsabc_backend.ijmsabc_java_backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Optional<Pdf> getPdfById(Long id) {
        return pdfRepository.findById(id);
    }

    // ✅ Correct Update (File Updates Only If New File Provided)
    public Pdf updatePdf(Long id, Pdf updatedPdf) {

        Pdf existingPdf = pdfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PDF not found with id: " + id));

        existingPdf.setTitle(updatedPdf.getTitle());
        existingPdf.setVolume(updatedPdf.getVolume());
        existingPdf.setIssueNo(updatedPdf.getIssueNo());
        existingPdf.setYear(updatedPdf.getYear());
        existingPdf.setType(updatedPdf.getType());
        existingPdf.setAuthor(updatedPdf.getAuthor());
        existingPdf.setDoi(updatedPdf.getDoi());
        existingPdf.setSource(updatedPdf.getSource());

        // ✅ Update file only if provided
        if (updatedPdf.getPdfDoc() != null) {
            existingPdf.setPdfDoc(updatedPdf.getPdfDoc());
        }

        return pdfRepository.save(existingPdf);
    }

    public void deletePdf(Long id) {
        pdfRepository.deleteById(id);
    }
}
