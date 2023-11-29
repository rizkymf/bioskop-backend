package org.binaracademy.bioskopbackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    public void upload(MultipartFile multipartFile);
}
