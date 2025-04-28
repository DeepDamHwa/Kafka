package com.example.kafka_consumer_01;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "topic02", groupId = "groupA")
    public void consumer(Object message) {
        System.out.println("Received message : " + message);
    }

//    @KafkaListener(topics = "topic02", groupId = "groupA")
//    public void consumer02(Object message) {
//        System.out.println("Received message : " + message);
//    }
//
//    @KafkaListener(topics = "topic02", groupId = "groupA")
//    public void consumer03(Object message) {
//        System.out.println("Received message : " + message);
//    }
}
