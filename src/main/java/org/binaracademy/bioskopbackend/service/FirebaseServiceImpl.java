package org.binaracademy.bioskopbackend.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FirebaseServiceImpl {

    @Autowired
    FirebaseMessaging firebaseMessaging;

    public void sendMsg(String messageToSent) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setTopic("project.firebase")
                .putData("body", messageToSent)
                .build();

        firebaseMessaging.send(message);
    }

    public String consumeMsg(String topic) throws FirebaseMessagingException {
        TopicManagementResponse response = firebaseMessaging.subscribeToTopic(null, "project.firebase");
        return null;
    }

}
