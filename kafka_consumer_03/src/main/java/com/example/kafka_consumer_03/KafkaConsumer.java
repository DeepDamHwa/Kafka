package com.example.kafka_consumer_03;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "topic01", groupId = "groupG")
    public void consumer(Object message) {
        System.out.println("Received message : " + message);
    }
}
