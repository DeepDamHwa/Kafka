package com.example.kafka_producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String,Object> kafkaTemplate;
    public void sendNewInteractionCaptureMessage(Object message, String key){

//        kafkaTemplate.send("topic01", message);
        kafkaTemplate.send("topic02", key,  message);
    }



//    Spring Kafka에서 KafkaTemplate를 통해 메시지를 보낼 때,
//    Key를 지정하지 않으면 Kafka 클라이언트는 “DefaultPartitioner” 라는 기본 파티셔너를 사용합니다.
//            “Sticky Partition” 방식이란?
//            •	Key가 없으면, 하나의 파티션을 한번 정한 뒤에 계속 그 파티션에 메시지를 몰아서 보냅니다.
//            •	어느 정도 데이터가 쌓이면 (예: 배치 사이즈 꽉 차거나, 타임아웃) 그때 다시 다른 파티션으로 바꿉니다.
//            •	완벽한 Round-Robin이 아님 ❌
//            •	Sticky = 일정 기간 동안 한 파티션에 몰아서 쓰는 방식.

// yml 설정해도 안됨

    // ->
//    Spring Kafka KafkaTemplate은 내부적으로 ProducerRecord를 만들 때
//    Key 없이 보내면 Sticky Partition을 사용하려고 합니다.
}