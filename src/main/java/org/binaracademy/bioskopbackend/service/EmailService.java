package org.binaracademy.bioskopbackend.service;

public interface EmailService {
    void sendEmail(String subject, String msg, String recipientEmail);
}
