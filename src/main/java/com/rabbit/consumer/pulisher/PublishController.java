package com.rabbit.consumer.pulisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublishController {
	@Autowired
	private SendMessageTemplate sendMessageTemplate;
	@RequestMapping("/send")
	@ResponseBody
	public void send(){
		sendMessageTemplate.sendMessage();
	}
}
