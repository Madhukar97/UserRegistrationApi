package com.bridgelabz.fundoo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.configuration.MQConfig;
import com.bridgelabz.fundoo.model.CustomMessage;

@Component
public class MessageListener{

	@RabbitListener(queues = MQConfig.QUEUE)
	public void listener(CustomMessage message) {
		System.out.println(message);
	}

}
