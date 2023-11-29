package org.binaracademy.bioskopbackend.service;

import org.binaracademy.bioskopbackend.model.request.EmailRequest;

public interface EmailService {
    void sendEmail(EmailRequest emailRequest);
}
