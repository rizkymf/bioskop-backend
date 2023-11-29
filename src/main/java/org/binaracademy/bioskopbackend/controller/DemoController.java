package org.binaracademy.bioskopbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.bioskopbackend.model.request.EmailRequest;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.binaracademy.bioskopbackend.service.CloudinaryService;
import org.binaracademy.bioskopbackend.service.EmailService;
//import org.binaracademy.bioskopbackend.service.FirebaseServiceImpl;
import org.binaracademy.bioskopbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/demo")
public class DemoController {

    // buat repository
    // buat service
    @Autowired
    MovieService movieService;

//    @Autowired
//    FirebaseServiceImpl firebaseService;

    @Autowired
    EmailService emailService;

    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping(value = "/multi-thread")
    public ResponseEntity getMultiThread() {
        Thread thread1 = new Thread(() -> {
            log.info("Starting thread1 : " + Thread.currentThread().getName());
            for(int i = 1; i <= 5; i++) {
                System.out.println(i);
                if(i == 3) {
                    try {
                        Thread.sleep(5000l);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            log.info("Starting thread2 : {}", Thread.currentThread().getName());
            for(int i = 100; i >= 95; i--) {
                System.out.println(i);
                if(i == 97) {
                    try {
                        Thread.sleep(3000l);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread1.start();
        try {
            thread1.join(1000l);
            thread1.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread2.start();
        return ResponseEntity.ok("oke");
    }

    @GetMapping(value = "/race-condition")
    public ResponseEntity getRaceCondition() {
        List<MovieResponse> movies = movieService.getAllMovie();
        System.out.println(movieService.getMovieDetail(movies.get(0).getName()).getId());
        return ResponseEntity.ok("ok");
    }

//    @PostMapping(value = "/firebase/publish")
//    public ResponseEntity publishToTopicFireBase(@RequestParam String message) throws FirebaseMessagingException {
//        firebaseService.sendMsg(message);
//        return ResponseEntity.ok()
//                .body("Success publish to topic firebase");
//    }

    @PostMapping(value = "/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request);
        return ResponseEntity.ok().body("Email sent!");
    }

    @GetMapping(value = "/test-ngrok")
    public String testNgrok(@RequestParam String message) {
        log.info("Message received : {}", message);
        return "Hello from Rizky service!";
    }

    @PostMapping(value = "/rest-template")
    public ResponseEntity restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "rapidapi.com";
        return restTemplate.getForEntity("https://imdb8.p.rapidapi.com/title/get-most-popular-movies?homeCountry=US&purchaseCountry=US&currentCountry=US",String.class);
//        return null;
    }

    @PostMapping(value = "/upload-image")
    public String testUploadImage(@RequestParam MultipartFile image) throws IOException {
        File file = new File(image.getOriginalFilename());
        FileOutputStream os = new FileOutputStream(file);
        os.write(image.getBytes());
        os.close();
        cloudinaryService.upload(image);
        return "upload success";
    }

}
