package com.github.marcelmfa.domain.task.service;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.marcelmfa.domain.task.Task;
import com.github.marcelmfa.domain.task.Ticket;
import com.github.marcelmfa.domain.task.inbound.CreateTaskMessage;
import com.github.marcelmfa.domain.task.repository.TaskRepository;
import com.github.marcelmfa.domain.task.repository.TicketRepository;

@Service
public class TaskService {
	
	private static final Logger log = LoggerFactory.getLogger(TaskService.class);

	private TaskRepository taskRepository;
	
	private TicketRepository ticketRepository;
	
	// Mocking number of existent tickets
	private AtomicLong numberTicketsToProcess = new AtomicLong((long) (Math.random() * 100));

	public TaskService(TaskRepository taskRepository, TicketRepository ticketRepository) {
		super();
		this.taskRepository = taskRepository;
		this.ticketRepository = ticketRepository;
	}

	public Task createTask(CreateTaskMessage message) {
		Task task = taskRepository.save(message.toTask());
		log.debug("Processing task: {}", task.getId());
		
		for (int index = 0; index < task.getTicketsToProcess(); index++) {
			if (hasTicketsToProcess()) {
				task.addTicketProcessed(processTicket());
				numberTicketsToProcess.decrementAndGet();
			} else {
				log.debug("No more tickets");
				break;
			}
		}
		
		log.debug("Number tickets processed: {}", task.getNumberTicketsProcessed());
		task.updateStatus();
		task = taskRepository.save(task);
		
		return task;
	}
	
	private Ticket processTicket() {
		return ticketRepository.save(new Ticket());
	}
	
	private boolean hasTicketsToProcess() {
		return numberTicketsToProcess.get() > 0L;
	}
}
