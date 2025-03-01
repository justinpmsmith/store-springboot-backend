package com.allianceseeds.api.adapters.notifications;

import java.io.ByteArrayInputStream;
import java.util.Base64;


import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailAdapter {

    @Autowired
    private final JavaMailSender mailSender;

    public EmailAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public Boolean sendEmail(String[] to, String subject, String body){
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(to);  // accepts an array of email addresses
            message.setSubject(subject);
            message.setText(body);

            System.out.println(to[0]);

            mailSender.send(message);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Async
    public Boolean sendEmailWithAttachments(String[] to, String subject, String body, String[] attachments) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            // true indicates multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            // Process each attachment
            for (int i = 0; i < attachments.length; i++) {
                String base64Data = attachments[i];

                // Remove Base64 prefix if it exists (data:image/png;base64, or data:image/jpeg;base64,)
                if (base64Data.contains(",")) {
                    base64Data = base64Data.split(",")[1];
                }

                // Decode Base64 to byte array
                byte[] imageData = Base64.getDecoder().decode(base64Data);

                String extension = ".png";
                String mimeType = "image/png";
                // Attempt to determine file type from Base64 prefix or just default to PNG
                if (attachments[i].contains("image/jpeg")) {
                    extension = ".jpg";
                    mimeType = "image/jpeg";
                }

                // Create a DataSource from the byte array
                DataSource dataSource = new ByteArrayDataSource(imageData, mimeType);

                // Attach the file with a generated name
                helper.addAttachment("image" + (i + 1) + extension, dataSource);
            }

            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
