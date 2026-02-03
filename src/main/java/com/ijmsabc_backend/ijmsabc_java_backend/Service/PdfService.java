

package com.ijmsabc_backend.ijmsabc_java_backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Pdf;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.PdfRepository;
@Service
public class PdfService {

    private final PdfRepository repo;

    public PdfService(PdfRepository repo) {
        this.repo = repo;
    }

    public Pdf save(Pdf pdf) {
        return repo.save(pdf);
    }

    public List<Pdf> getAll() {
        return repo.findAll();
    }

    public Pdf getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("PDF Not Found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
