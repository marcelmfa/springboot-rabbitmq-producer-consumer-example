package com.github.marcelmfa.domain.task.inbound;

import com.github.marcelmfa.domain.task.Task;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class CreateTaskMessage {

	private String usernameAssigned;
	
	private Integer ticketsToProcess;
	
	public Task toTask() {
		return new Task(usernameAssigned, ticketsToProcess);
	}
}
