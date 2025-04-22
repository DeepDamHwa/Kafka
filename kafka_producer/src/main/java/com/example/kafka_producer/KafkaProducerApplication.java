package com.example.kafka_producer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class KafkaProducerApplication implements CommandLineRunner {
    private final KafkaProducer kafkaProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaProducer.sendNewInteractionCaptureMessage("hello, DeepDamHwa");
    }
}
