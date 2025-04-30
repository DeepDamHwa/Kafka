package com.example.kafka_producer;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka // Kafka 관련 기능을 활성화 (사실 Producer 쪽에는 없어도 되지만, 있어도 무방함)
@Configuration // 이 클래스가 스프링 설정 클래스임을 명시
public class KafkaProducerConfig {

    // Kafka 브로커 주소 설정 (여기선 하드코딩, 실제 운영에서는 @Value로 외부에서 주입하는 것이 일반적)
    // @Value("${custom.kafka-url}") 를 통해 yml/properties에서 주입 가능
    private String kafkaURL = "192.168.25.87:9092";

    // Kafka ProducerFactory 빈 생성
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        // Kafka 브로커 주소
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURL);

        // key 직렬화 방식: 문자열 (StringSerializer)
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // value 직렬화 방식: JSON 객체를 바이너리로 변환 (JsonSerializer)
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // 설정을 기반으로 Kafka Producer Factory 생성
        return new DefaultKafkaProducerFactory<>(config);
    }

    // KafkaTemplate 빈 생성 - 실제로 메시지를 보낼 때 사용
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        // 위에서 설정한 producerFactory를 사용하여 KafkaTemplate 생성
        return new KafkaTemplate<>(producerFactory());
    }
}