//package org.binaracademy.bioskopbackend.listener;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MovieKafkaPublisher {
//
//    @Autowired
//    KafkaTemplate<String, String> kafkaTemplate;
//
//    public void publishData(String data) {
//        kafkaTemplate.send("movie.new", data);
//    }
//}
