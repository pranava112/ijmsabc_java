package com.ijmsabc_backend.ijmsabc_java_backend.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ijmsabc_backend.ijmsabc_java_backend.Entity.Manuscript;
import com.ijmsabc_backend.ijmsabc_java_backend.Repository.ManuscriptRepository;

import jakarta.mail.MessagingException;

@Service
public class ManuscriptService {

    private final ManuscriptRepository manuscriptRepository;
    private final JavaMailSender mailSender;
    private final CertificateService certificateService;

    public ManuscriptService(ManuscriptRepository manuscriptRepository,
                             JavaMailSender mailSender,
                             CertificateService certificateService) {
        this.manuscriptRepository = manuscriptRepository;
        this.mailSender = mailSender;
        this.certificateService = certificateService;
    }

    // Save manuscript and send email confirmation + certificate
    public Manuscript saveManuscript(Manuscript manuscript) {
        Manuscript saved = manuscriptRepository.save(manuscript);
        sendConfirmationEmail(saved.getName(), saved.getEmail(), saved.getTitle());
        try {
            certificateService.sendCertificateByEmail(saved.getEmail(), saved.getName(), saved.getTitle());
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
  

            System.err.println("Failed to send certificate: " + e.getMessage());
        }
        return saved;
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
                    existingManuscript.setPhone(updatedManuscript.getPhone());
                    existingManuscript.setTitle(updatedManuscript.getTitle());
                    existingManuscript.setAbst(updatedManuscript.getAbst());
                     existingManuscript.setSource(updatedManuscript.getSource());

                    existingManuscript.setKwords(updatedManuscript.getKwords());
                    if (updatedManuscript.getPdfDoc() != null) {
                        existingManuscript.setPdfDoc(updatedManuscript.getPdfDoc());
                    }
                    return manuscriptRepository.save(existingManuscript);
                })
                .orElseThrow(() -> new RuntimeException("Manuscript not found with id: " + id));
    }

    public void deleteManuscript(Long id) {
        manuscriptRepository.deleteById(id);
    }

    // ✅ Basic confirmation email
    private void sendConfirmationEmail(String name, String email, String title) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Manuscript Submission Confirmation - IJMSABC");
        message.setText("Dear " + name + ",\n\nThank you for submitting your manuscript titled \"" +
                title + "\".\nOur editorial team will review it shortly.\n\nBest regards,\nIJMSABC Editorial Office");
        mailSender.send(message);
    }
}
