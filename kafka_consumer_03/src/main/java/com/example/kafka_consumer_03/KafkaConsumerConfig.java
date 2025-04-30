package com.example.kafka_consumer_03;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka // Kafka 리스너(@KafkaListener)를 사용하기 위한 어노테이션
@Configuration // 스프링 설정 클래스임을 명시
public class KafkaConsumerConfig {

    // Kafka 브로커 주소 (하나만 지정함)
    // 필요 시 여러 브로커를 쉼표로 구분하여 입력 가능
    private String kafkaURL = "192.168.25.87:9092";
//    private String kafkaURL = "192.168.25.87:9092,192.168.25.87:9093,192.168.25.87:9094";

    // Kafka ConsumerFactory 빈 생성
    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        // Kafka 브로커 주소
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURL);

        // Consumer Group ID 지정
        // @KafkaListener에 groupId가 없으면 이 설정이 기본값으로 사용됨
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "basic-group");

        // offset이 없을 경우 가장 오래된 메시지부터 읽음 (earliest)
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // key 역직렬화 방식: 문자열
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // value 역직렬화 방식: JSON
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // 어떤 패키지의 클래스든 역직렬화 허용 (보안 주의 필요)
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        // 설정된 옵션으로 ConsumerFactory 생성
        return new DefaultKafkaConsumerFactory<>(config);
    }

    // KafkaListenerContainerFactory 빈 생성
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        // 병렬 리스너 컨테이너 팩토리 생성
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        // 위에서 설정한 ConsumerFactory 주입
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}