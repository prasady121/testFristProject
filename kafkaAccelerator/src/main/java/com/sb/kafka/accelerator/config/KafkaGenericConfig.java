
  package com.sb.kafka.accelerator.config;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.beans.factory.annotation.Value; import
  org.springframework.boot.autoconfigure.kafka.KafkaProperties; import
  org.springframework.context.annotation.Bean; import
  org.springframework.context.annotation.Configuration; import
  org.springframework.kafka.core.ConsumerFactory; import
  org.springframework.kafka.core.DefaultKafkaConsumerFactory; import
  org.springframework.kafka.core.DefaultKafkaProducerFactory; import
  org.springframework.kafka.core.KafkaTemplate; import
  org.springframework.kafka.core.ProducerFactory;
  
  @Configuration public class KafkaGenericConfig {
  
  @Value("${spring.kafka.producer.topic}") private String topic;
  
  @Autowired private KafkaTemplate<String, Object> kafkaTemplate;
  
  @Bean public KafkaTemplate<String, Object>
  kafkaTemplate(ProducerFactory<String, Object> producerFactory) { return new
  KafkaTemplate(producerFactory); }
  
  @Bean public ProducerFactory<String, Object> producerFactory(KafkaProperties
  kafkaProperties) { return new
  DefaultKafkaProducerFactory(kafkaProperties.buildProducerProperties()); }
  
  @Bean public ConsumerFactory<String, Object> consumerFactory(KafkaProperties
  kafkaProperties) { return new
  DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties()); } }
 