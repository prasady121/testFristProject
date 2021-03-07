package com.sb.kafka.accelerator.producer;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSyncProducer {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaSyncProducer.class);
	
	@Autowired
	private KafkaTemplate<String, Object> template;
		
	@SuppressWarnings("unchecked")
	public void runSyncProducer(String topic, List<Object> recordList) throws Exception {
		long time = System.currentTimeMillis();
		int messageCount = 0;
		ProducerRecord<String, Object> producerRecord = null;
		try {
			log.debug(String.format("Inside KafkaSyncProducer.runSyncProducer for the topic :%s", topic));
			messageCount = recordList.size();
			if (messageCount > 1) {
				log.debug("KafkaSyncProducer.runSyncProducer message count is :%d", messageCount);
				for (Object record : recordList) {
					log.debug(String.format("KafkaSyncProducer.runSyncProducer record :%s", record.toString()));
					Map<String, Object> mapRecord = ((Map<String, Object>) record);
					producerRecord = new ProducerRecord<>(topic, mapRecord);
					template.send(producerRecord);
					
					long elapsedTime = System.currentTimeMillis() - time;
					log.debug("sent record(key=%s value=%s) " + " time=%d\n", producerRecord.key(),
					producerRecord.value(), elapsedTime);
				}
			} else {
				Object record = recordList.get(0);
				log.debug(String.format("KafkaSyncProducer.runSyncProducer record :%s", record.toString()));
				Map<String, Object> mapRecord = ((Map<String, Object>) record);
				producerRecord = new ProducerRecord<>(topic, mapRecord);
				template.send(producerRecord);
				long elapsedTime = System.currentTimeMillis() - time;
				log.debug("sent record(key=%s value=%s) " + " time=%d\n", producerRecord.key(), producerRecord.value(),
							elapsedTime);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


}
