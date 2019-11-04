package com.github.marcelmfa.domain.task.controller.v1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.marcelmfa.domain.task.controller.v1.inbound.CreateTaskAdapter;
import com.github.marcelmfa.domain.task.service.TaskService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tasks")
public class CreateTaskController {

	private TaskService taskService;
	
	public CreateTaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	@PostMapping
	public Mono<Void> createTask(@RequestBody CreateTaskAdapter body) {
		return Mono.just(body)
				.doOnNext(taskService::sendMessageCreateTask)
				.then();
	}
}
