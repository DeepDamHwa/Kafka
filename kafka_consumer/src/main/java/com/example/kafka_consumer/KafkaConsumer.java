package com.example.kafka_consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "topic", groupId = "basic-group")
    public void listen(Object message) {
        System.out.println("Received message: " + message);
    }
}
