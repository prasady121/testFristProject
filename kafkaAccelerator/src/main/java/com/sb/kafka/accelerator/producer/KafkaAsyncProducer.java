package com.sb.kafka.accelerator.producer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaAsyncProducer {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaAsyncProducer.class);
	
	
	@Autowired
	private KafkaTemplate<String, Object> template;
		
	@SuppressWarnings("unchecked")
	public void runAsyncProducer(String topic,
				List<Object> recordList) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(recordList.size());
		int sendMessageCount = 0;
		try {
			log.debug(String.format("Inside KafkaAsyncProducer.runAsyncProducer for the topic :%s", topic));
			sendMessageCount = recordList.size();
			if (sendMessageCount > 1) {
				log.debug("KafkaAsyncProducer.runAsyncProducer message count is :%d", sendMessageCount);
				for (Object record : recordList) {
							
	log.debug(String.format("KafkaAsyncProducer.runAsyncProducer record :%s", record.toString()));
					Map<String, Object> mapRecord = ((Map<String, Object>) record);
					final ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, mapRecord);
					template.send(producerRecord);
					log.debug("KafkaAsyncProducer.runAsyncProducer record sent to kafka topic");
				}
				countDownLatch.await(25, TimeUnit.SECONDS);
			} else {
				if (recordList.get(0) != null) {
							
				log.debug(String.format("KafkaAsyncProducer.runAsyncProducer record :%s", recordList.get(0).toString()));
					Map<String, Object> mapRecord = ((Map<String, Object>) recordList.get(0));
					final ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, mapRecord);
					template.send(producerRecord);
					log.debug("KafkaAsyncProducer.runAsyncProducer record sent to kafka topic");
				}
				countDownLatch.await(25, TimeUnit.SECONDS);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
