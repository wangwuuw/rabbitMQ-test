package com.rabbit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rabbit.RabbitMQApplication;
import com.rabbit.consumer.pulisher.SendMessageTemplate;


/**
 * Created by zhangjinyang on 2018/1/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RabbitMQApplication.class)
public class RabbitMQTest{
@Autowired
private SendMessageTemplate sendMessageTemplate;
@Test
public void sendMessage(){
	sendMessageTemplate.sendMessage();
}
 
}
