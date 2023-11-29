package org.binaracademy.bioskopbackend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.binaracademy.bioskopbackend.service.CloudinaryService;
import org.binaracademy.bioskopbackend.service.CloudinaryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "<cloud-name>",
                "api_key", "<cloudinary-api-key>",
                "api_secret", "<cloudinary-api-secret>"));
    }

    @Bean
    public CloudinaryService cloudinaryService() {
        return new CloudinaryServiceImpl(cloudinary());
    }
}
