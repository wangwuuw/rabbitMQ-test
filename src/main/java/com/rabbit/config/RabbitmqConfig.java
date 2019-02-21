package com.rabbit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

//@ConfigurationProperties(locations = "classpath:config/rabbitmqConfig.properties")
@Data
@Component
public class RabbitmqConfig {

	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbitmq.transferInKey}")
	private String transferInKey;

	@Value("${rabbitmq.transferInQueue}")
	private String transferInQueue;

}
