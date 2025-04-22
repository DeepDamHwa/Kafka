package com.example.kafka_producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String,Object> kafkaTemplate;
    public void sendNewInteractionCaptureMessage(Object message){

        kafkaTemplate.send("topic", message);
        System.out.println("ddd");
    }
}