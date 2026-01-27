package com.ijmsabc_backend.ijmsabc_java_backend.Service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class CertificateService {

    private final JavaMailSender mailSender;

    public CertificateService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public byte[] generateCertificatePdf(String name, String title) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);

        PDRectangle rect = page.getMediaBox();
        float width = rect.getWidth();
        float height = rect.getHeight();

        float margin = 40;
        PDPageContentStream cs = new PDPageContentStream(document, page, AppendMode.APPEND, true);

        // --- Background ---
        Color bgColor = new Color(245, 248, 255); // soft light blue background
        cs.setNonStrokingColor(bgColor);
        cs.addRect(0, 0, width, height);
        cs.fill();

        // --- Border ---
        Color borderColor = new Color(0, 51, 102);
        cs.setStrokingColor(borderColor);
        cs.setLineWidth(3);
        cs.addRect(margin, margin, width - (2 * margin), height - (2 * margin));
        cs.stroke();

        // --- Add Rounded Logo ---
        try (InputStream logoStream = new ClassPathResource("certificate/IjmsabcLogo.png").getInputStream()) {
            BufferedImage logoImage = ImageIO.read(logoStream);
            BufferedImage roundedLogo = makeRoundedCorner(logoImage, 200);

            PDImageXObject logo = PDImageXObject.createFromByteArray(document, toByteArray(roundedLogo, "png"), "logo");
            float logoWidth = 100;
            float logoHeight = 100;
            cs.drawImage(logo, (width / 2) - (logoWidth / 2), height - 180, logoWidth, logoHeight);
        }

        // --- Journal Title (centered, split into 3 lines) ---
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_BOLD, 14); // reduced font size to fit
        cs.setNonStrokingColor(new Color(0, 43, 92));
        String line1 = "INTERNATIONAL JOURNAL OF";
        float line1Width = (PDType1Font.TIMES_BOLD.getStringWidth(line1) / 1000) * 14;
        cs.newLineAtOffset((width - line1Width) / 2, height - 200);
        cs.showText(line1);
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_BOLD, 14);
        cs.setNonStrokingColor(new Color(0, 43, 92));
        String line2 = "MANAGEMENT SCIENCE AND BUSINESS CONCLAVE";
        float line2Width = (PDType1Font.TIMES_BOLD.getStringWidth(line2) / 1000) * 14;
        cs.newLineAtOffset((width - line2Width) / 2, height - 220);
        cs.showText(line2);
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_BOLD, 14);
        cs.setNonStrokingColor(new Color(0, 43, 92));
        String line3 = "(IJMSABC)";
        float line3Width = (PDType1Font.TIMES_BOLD.getStringWidth(line3) / 1000) * 14;
        cs.newLineAtOffset((width - line3Width) / 2, height - 240);
        cs.showText(line3);
        cs.endText();

        // --- Certificate Heading ---
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_BOLD, 32);
        cs.setNonStrokingColor(Color.BLACK);
        String certHeader = "CERTIFICATE";
        float certWidth = (PDType1Font.TIMES_BOLD.getStringWidth(certHeader) / 1000) * 32;
        cs.newLineAtOffset((width - certWidth) / 2, height - 280);
        cs.showText(certHeader);
        cs.endText();

        // --- Subheading ---
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 16);
        cs.setNonStrokingColor(Color.BLACK);
        String subHeader = "THIS IS TO CERTIFY THAT";
        float subWidth = (PDType1Font.TIMES_ROMAN.getStringWidth(subHeader) / 1000) * 16;
        cs.newLineAtOffset((width - subWidth) / 2, height - 320);
        cs.showText(subHeader);
        cs.endText();

        // --- Recipient Name ---
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_BOLD, 28);
        cs.setNonStrokingColor(new Color(128, 0, 0)); // Maroon
        float nameWidth = (PDType1Font.TIMES_BOLD.getStringWidth(name) / 1000) * 28;
        cs.newLineAtOffset((width - nameWidth) / 2, height - 360);
        cs.showText(name);
        cs.endText();

        // --- Manuscript Title Line ---
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_BOLD, 18);
        cs.setNonStrokingColor(Color.BLACK);
        float manWidth = (PDType1Font.TIMES_BOLD.getStringWidth(title) / 1000) * 18;
        cs.newLineAtOffset((width - manWidth) / 2, height - 400);
        cs.showText(title);
        cs.endText();

        // --- Award Line (center aligned properly) ---
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 12);
        cs.setNonStrokingColor(Color.BLACK);
        String awardLine = "HAS BEEN AWARDED THIS CERTIFICATE FOR SUBMITTING A MANUSCRIPT TO IJMSABC";
        float awardWidth = (PDType1Font.TIMES_ROMAN.getStringWidth(awardLine) / 1000) * 12;
        cs.newLineAtOffset((width - awardWidth) / 2, height - 440);
        cs.showText(awardLine);
        cs.endText();

        // --- Date ---
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_BOLD, 14);
        float dateWidth = (PDType1Font.TIMES_BOLD.getStringWidth(date) / 1000) * 14;
        cs.newLineAtOffset((width - dateWidth) / 2, height - 490);
        cs.showText(date);
        cs.endText();

        // --- Signature ---
        cs.beginText();
        cs.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
        cs.newLineAtOffset(width / 2 - 50, height - 540);
        cs.showText("Dr. V. Vijaya Kumar");
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.TIMES_ROMAN, 12);
        cs.newLineAtOffset(width / 2 - 35, height - 555);
        cs.showText("Editor-in-Chief");
        cs.endText();

        cs.close();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        document.close();
        return out.toByteArray();
    }

    /** Converts BufferedImage to ByteArray */
    private byte[] toByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        return baos.toByteArray();
    }

    /** Creates a circular/rounded image */
    private BufferedImage makeRoundedCorner(BufferedImage image, int diameter) {
        BufferedImage output = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D.Double clip = new Ellipse2D.Double(0, 0, diameter, diameter);
        g2.setClip(clip);
        g2.drawImage(image, 0, 0, diameter, diameter, null);
        g2.dispose();
        return output;
    }

    /** Email sender for the certificate */
    public void sendCertificateByEmail(String toEmail, String name, String title)
            throws MessagingException, IOException {

        byte[] pdfBytes = generateCertificatePdf(name, title);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("ijmsbc@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject("Certificate of Submission — " + title);

        String body = """
                Dear %s,

                Thank you for submitting your manuscript titled "%s" to IJMSABC.
                Please find attached your Certificate of Submission.

                Best regards,
                Dr. V. Vijaya Kumar
                Editor-in-Chief, IJMSABC
                """.formatted(name, title);

        helper.setText(body);
        helper.addAttachment("Certificate_of_Submission.pdf", new ByteArrayResource(pdfBytes));

        mailSender.send(message);
    }
}