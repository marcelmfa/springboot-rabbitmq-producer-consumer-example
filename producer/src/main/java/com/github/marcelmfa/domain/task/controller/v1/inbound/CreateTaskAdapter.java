package com.github.marcelmfa.domain.task.controller.v1.inbound;

import com.github.marcelmfa.domain.task.messaging.CreateTaskOutbound;

import lombok.Data;

@Data
public class CreateTaskAdapter {

	private String usernameAssigned;
	
	private Integer ticketsToProcess;
	
	public CreateTaskOutbound toCreateTaskOutbound() {
		return new CreateTaskOutbound(usernameAssigned, ticketsToProcess);
	}
}
