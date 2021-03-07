package com.sb.kafka.accelerator.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaAcceleratorConsumerService {
	
	@KafkaListener(topics = "test")
	public void consume(ConsumerRecord<String, Object> message) {
		System.out.println("Consumer Record :" + message);
	}

}
