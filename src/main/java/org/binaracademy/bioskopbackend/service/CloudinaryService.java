package org.binaracademy.bioskopbackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    public String upload(MultipartFile multipartFile);
}
