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

    public Pdf updatePdf(Long id, Pdf updatedPdf) {
        return pdfRepository.findById(id)
                .map(existingPdf -> {

                    existingPdf.setTitle(updatedPdf.getTitle());
                    existingPdf.setVolume(updatedPdf.getVolume());
                    existingPdf.setIssueNo(updatedPdf.getIssueNo());
                    existingPdf.setYear(updatedPdf.getYear());
                    existingPdf.setType(updatedPdf.getType());
                    existingPdf.setAuthor(updatedPdf.getAuthor());
                    existingPdf.setSource(updatedPdf.getSource());
                     existingPdf.setDoi(updatedPdf.getDoi());

                    // 🔑 Only replace link if new link provided
                    if (updatedPdf.getPdfLink() != null && !updatedPdf.getPdfLink().isEmpty()) {
                        existingPdf.setPdfLink(updatedPdf.getPdfLink());
                    }

                    return pdfRepository.save(existingPdf);
                })
                .orElseThrow(() ->
                        new RuntimeException("Pdf not found with id: " + id));
    }

    public void deletePdf(Long id) {
        pdfRepository.deleteById(id);
    }
}
