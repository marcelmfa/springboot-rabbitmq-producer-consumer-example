package com.github.marcelmfa.domain.task.service;

import org.springframework.stereotype.Service;

import com.github.marcelmfa.component.RabbitMQSender;
import com.github.marcelmfa.domain.task.controller.v1.inbound.CreateTaskAdapter;
import com.github.marcelmfa.domain.task.messaging.CreateTaskOutbound;

@Service
public class TaskService {

	private RabbitMQSender<CreateTaskOutbound> rabbitMQSender;

	public TaskService(RabbitMQSender<CreateTaskOutbound> sender) {
		super();
		this.rabbitMQSender = sender;
	}
	
	public void sendMessageCreateTask(CreateTaskAdapter inbound) {
		CreateTaskOutbound outbound = inbound.toCreateTaskOutbound();
		rabbitMQSender.send(outbound);
	}
}
