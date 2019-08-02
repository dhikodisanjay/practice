package com.configuration;

import com.com.model.Users;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

	public KafkaConfiguration() {
	}

	@Bean
	public ProducerFactory producerFactory() {

		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory(config);
	}

	@Bean(name = "kafkaTemplate1")
	public KafkaTemplate<String, Users> kafkaTemplate1() {

		return new KafkaTemplate<String, Users>(producerFactory());
	}

	@Bean(name = "kafkaTemplate2")
	public KafkaTemplate<String, String> kafkaTemplate2() {

		return new KafkaTemplate<String, String>(producerFactory());
	}

	@Bean
	public ConsumerFactory<String, Users> consumerFactory() {
		Map<String, Object> config = new HashMap<String, Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES,"com.com.model.*");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "mygroup");

		// return new DefaultKafkaConsumerFactory<String, Users>(config);

		return new DefaultKafkaConsumerFactory(config, new StringDeserializer(), new JsonDeserializer<Users>());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,Users> kafkaListenerContainerFactory(){
		
		
		ConcurrentKafkaListenerContainerFactory<String,Users>  kafkaListenerContainerFactory= new ConcurrentKafkaListenerContainerFactory<String, Users>();
		
		kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
		
		
		return kafkaListenerContainerFactory;
		
	}
	
	
	

}
