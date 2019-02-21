package com.rabbit.consumer.pulisher;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

@Slf4j
@Service("callBack")
public class SendCallBack implements ConfirmCallback {

//	@Autowired
//	private SendMessageService sendMessageService;

//	@Autowired
//	private ProduceLogCache ProduceLogCache;

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (null == correlationData)
			return;
		String id = correlationData.getId();
		if (ack) {
			try {
				System.out.println("确认已经发送");
				Integer result = 1;
//				Integer result = sendMessageService.updateMessageStatus(Long.valueOf(id), SendMessageStatus.COMMITED,
//						(int) (Long.parseLong(id) % 10));
				if (result <= 0) {
					log.error("确认消息id" + id + "失败无法查到此记录");
				}
				System.out.println("从缓存中删除消息id");
//				ProduceLogCache.remove(Long.valueOf(id));

			} catch (Exception e) {
				log.error("确认消息id" + id + "失败" + e.getMessage());
			}

		} else {
			log.error("消息id:" + id + "发送失败,失败原因" + cause);
		}

	}

}
