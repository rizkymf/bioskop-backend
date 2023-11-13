package org.binaracademy.bioskopbackend.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.binaracademy.bioskopbackend.service.FirebaseServiceImpl;
import org.binaracademy.bioskopbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/demo")
public class DemoController {

    // buat repository
    // buat service
    @Autowired
    MovieService movieService;

    @Autowired
    FirebaseServiceImpl firebaseService;
    // buat controller

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

    @PostMapping(value = "/firebase/publish")
    public ResponseEntity publishToTopicFireBase(@RequestParam String message) throws FirebaseMessagingException {
        firebaseService.sendMsg(message);
        return ResponseEntity.ok()
                .body("Success publish to topic firebase");
    }

}
