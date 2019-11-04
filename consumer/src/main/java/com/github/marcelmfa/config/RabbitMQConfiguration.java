package com.github.marcelmfa.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marcelmfa.domain.task.inbound.CreateTaskMessage;
import com.github.marcelmfa.domain.task.service.TaskService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RabbitMQConfiguration {

	private static final String TASKS_QUEUE_NAME = "tasks";
	
	private TaskService taskService;

	public RabbitMQConfiguration(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	
	@Bean
	public Queue queue() {
		return new Queue(TASKS_QUEUE_NAME);
	}
	
	@RabbitListener(queues = TASKS_QUEUE_NAME)
	public void rabbitListener(String in) {
		log.debug("Message received {}", in.toString());
		CreateTaskMessage ctm = new Gson().fromJson(in, CreateTaskMessage.class);
		taskService.createTask(ctm);
	}
}
