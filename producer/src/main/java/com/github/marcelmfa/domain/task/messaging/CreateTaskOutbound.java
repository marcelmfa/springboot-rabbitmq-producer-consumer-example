package com.github.marcelmfa.domain.task.messaging;

import java.io.Serializable;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CreateTaskOutbound implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5991456158913802203L;

	@NonNull
	private String usernameAssigned;

	@NonNull
	private Integer ticketsToProcess;

}
