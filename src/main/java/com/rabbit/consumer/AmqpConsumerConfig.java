package com.rabbit.consumer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import com.rabbit.config.RabbitmqConfig;

@Configuration
public class AmqpConsumerConfig implements RabbitListenerConfigurer{
	
	public static final Integer MQ_PREFETCH_COUNT = 2;
	
	@Autowired
	private RabbitmqConfig rabbitConfig;
	
	public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(new MappingJackson2MessageConverter());
		return factory;
	}
	
	public void configureRabbitListeners(
			RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());		
	}
	@Bean("SimpleRabbitListenerContainerFactory")
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		System.out.println(connectionFactory.hashCode());
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setPrefetchCount(MQ_PREFETCH_COUNT);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(rabbitConfig.getExchange());
	}
	// ������Ѷ���
	@Bean("transferInCoinQueue")
	public Queue transferInCoinQueue() {
		return new Queue(rabbitConfig.getTransferInQueue(), true, false, false);
	}
	@Bean
	public Binding blindingTransferInQueue(@Qualifier("transferInCoinQueue") Queue transferInCoinQueue) {
		return BindingBuilder.bind(transferInCoinQueue).to(exchange()).with(rabbitConfig.getTransferInKey());
	}
	@Bean
	public SimpleMessageListenerContainer TransferInCoinContainer(
			@Qualifier("transferInListener") MessageListener transferInListener, ConnectionFactory connectionFactory) {
		System.out.println(connectionFactory.hashCode());
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(transferInCoinQueue());
		container.setExposeListenerChannel(true);
		container.setMaxConcurrentConsumers(1);
		container.setConcurrentConsumers(1);
		container.setAcknowledgeMode(AcknowledgeMode.AUTO); // 智能确认
		container.setMessageListener(transferInListener);
		return container;
	}
//	@Bean
//	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
//	    return new RabbitAdmin(connectionFactory);
//	}
}
