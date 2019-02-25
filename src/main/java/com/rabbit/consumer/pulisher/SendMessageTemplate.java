package com.rabbit.consumer.pulisher;

import java.math.BigDecimal;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.rabbit.config.RabbitmqConfig;
import com.rabbit.entity.DepositCoinAssetStatus;
import com.rabbit.entity.TransferInDto;
@Slf4j
@Component
public class SendMessageTemplate {
	@Autowired
	RabbitTemplate rabbitMqTemplete;
	@Autowired
	private RabbitmqConfig rabbitConfig;
	public void sendMessage(){
		String exchange = rabbitConfig.getExchange();
		String key = rabbitConfig.getTransferInKey();
		Long id = new Random().nextLong();
//		   TransferCoinMessage transferCoinMessage = new TransferCoinMessage();
//	        transferCoinMessage.setTxid(order.getInnerOrderNo());
//	        transferCoinMessage.setAddress(order.getCoinAddress());
//	        transferCoinMessage.setAmount(order.getRealNumber().toString());
//	        transferCoinMessage.setTxfee(order.getTxFee().toString());
//	        transferCoinMessage.setMessage(order.getMsg());
		TransferInDto transferInDto = new TransferInDto();
		transferInDto.setAmount(new BigDecimal("100"));
		transferInDto.setAssetCode("RMB");
		transferInDto.setAssetStatus(DepositCoinAssetStatus.SS);
		transferInDto.setFromWallet("wangwu");
		transferInDto.setOrderId(id.toString());
		transferInDto.setSendMessage("111");
		transferInDto.setToWallet("mayun");
		Object message = JSON.toJSON(transferInDto);//"消息编号为"+id;
		try {
			MessageProperties messageProperties = new MessageProperties();

			String correlationDataId = id.toString();
			messageProperties.setMessageId(correlationDataId);
			messageProperties.setCorrelationId(correlationDataId.getBytes());
			Message rabbitmqMessage = new Message(message.toString().getBytes(), messageProperties);

			rabbitMqTemplete.send(exchange, key, rabbitmqMessage, new CorrelationData(correlationDataId));
			// rabbitMqTemplete.convertAndSend(exchange, key, message, new
			// CorrelationData(id.toString()));
			log.info("发送消息成功编号为"+id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送mq异常" + e);
		}
	}
}
