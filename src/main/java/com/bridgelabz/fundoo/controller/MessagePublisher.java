package com.bridgelabz.fundoo.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.configuration.MQConfig;
import com.bridgelabz.fundoo.model.CustomMessage;

@RestController
public class MessagePublisher {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private CustomMessage message;


	public String publishMessage( String msg) {
		message.setMessage(msg);
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageDate(new Date());
		template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, message);
		return "Message Published";

	}

}
