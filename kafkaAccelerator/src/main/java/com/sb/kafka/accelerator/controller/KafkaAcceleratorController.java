package com.sb.kafka.accelerator.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sb.kafka.accelerator.service.KafkaAcceleratorProducerService;

@RestController 
public class KafkaAcceleratorController {	
	@Autowired
	private KafkaAcceleratorProducerService service;

	@PostMapping(value = "/send/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void send(@PathVariable("type") String type, @RequestBody List<Object> recordList) throws Exception {
	if (recordList.size() != 0) {
			System.out.println("Record size :" + recordList.size());
			if ("sync".contentEquals(type)) {
				System.out.println("Here in Sync");
				service.sendSync(recordList);
				System.out.println("Message sent to topic:test, message:" + recordList);
			} else {
				System.out.println("Here in Async");
				service.sendAsync(recordList);
			}
		}
	}

}
