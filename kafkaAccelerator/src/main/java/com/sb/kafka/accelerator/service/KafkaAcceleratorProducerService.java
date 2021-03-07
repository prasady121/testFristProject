package com.sb.kafka.accelerator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.kafka.accelerator.producer.KafkaAsyncProducer;
import com.sb.kafka.accelerator.producer.KafkaSyncProducer;

@Service
public class KafkaAcceleratorProducerService {
	
	
	@Autowired
	private KafkaSyncProducer syncProducer;
		
	@Autowired
	private KafkaAsyncProducer asyncProducer;
		
	public String sendSync(List<Object> recordList) throws Exception {
		syncProducer.runSyncProducer("test", recordList);
		return "Message sent sync";
	}
		
	public String sendAsync(List<Object> recordList) throws Exception {
		asyncProducer.runAsyncProducer("test", recordList);
		return "Message sent async";
	}

	
	
	

}
