//package org.binaracademy.bioskopbackend.listener;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.binaracademy.bioskopbackend.model.Movie;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MovieKafkaListener {
//
//    @KafkaListener(topics = {"movie.new"})
//    public void listenMovie(Movie movie) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writeValueAsString(movie));
//    }
//}
