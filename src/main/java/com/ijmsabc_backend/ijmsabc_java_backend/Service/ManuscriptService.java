package com.ijmsabc_backend.ijmsabc_java_backend.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Manuscript;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.ManuscriptRepository;


@Service
public class ManuscriptService {

    private final ManuscriptRepository manuscriptRepository;

    public ManuscriptService(ManuscriptRepository manuscriptRepository) {
        this.manuscriptRepository = manuscriptRepository;
    }

    public Manuscript saveManuscript(Manuscript manuscript) {
        return manuscriptRepository.save(manuscript);
    }

    public List<Manuscript> getAllManuscripts() {
        return manuscriptRepository.findAll();
    }

    public Optional<Manuscript> getManuscriptById(Long id) {
        return manuscriptRepository.findById(id);
    }

    public Manuscript updateManuscript(Long id, Manuscript updatedManuscript) {
        return manuscriptRepository.findById(id)
                .map(existingManuscript -> {
                    existingManuscript.setName(updatedManuscript.getName());
                    existingManuscript.setEmail(updatedManuscript.getEmail());
                    existingManuscript.setTitle(updatedManuscript.getTitle());
                    existingManuscript.setAbst(updatedManuscript.getAbst());
                    existingManuscript.setKwords(updatedManuscript.getKwords()); // ✅ corrected
                    existingManuscript.setPdfDoc(updatedManuscript.getPdfDoc());     // ✅ check this matches entity
                    return manuscriptRepository.save(existingManuscript);
                })
                .orElseThrow(() -> new RuntimeException("Manuscript not found with id: " + id));
    }

    public void deleteManuscript(Long id) {
        manuscriptRepository.deleteById(id);
    }

    // return manuscriptService.getManuscriptById(id);
}
