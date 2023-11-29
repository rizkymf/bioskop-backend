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
                "cloud_name", "test-rizky",
                "api_key", "189827629937255",
                "api_secret", "rH3rKU4pY5_xn19cYWQvXR2A8gs"));
    }

    @Bean
    public CloudinaryService cloudinaryService() {
        return new CloudinaryServiceImpl(cloudinary());
    }
}
