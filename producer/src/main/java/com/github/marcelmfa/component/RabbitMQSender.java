package com.github.marcelmfa.component;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQSender<T> {

	private static final String TASKS_QUEUE_NAME = "tasks";
	
	private RabbitTemplate rabbitTemplate;
	
	public RabbitMQSender(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void send(T msg) {
		String json = new Gson().toJson(msg);
		log.debug("Sending message {}", json);
		rabbitTemplate.convertAndSend(TASKS_QUEUE_NAME, json);
	}
	
}
