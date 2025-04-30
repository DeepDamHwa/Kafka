package com.example.kafka_consumer_02;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "topic01", groupId = "groupA")
    public void consumer(Object message) {
        System.out.println("Received message : " + message);
    }
}
