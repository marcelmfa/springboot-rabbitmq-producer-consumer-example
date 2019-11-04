package com.github.marcelmfa.domain.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
public class Task extends AbstractPersistable<Long> {

	@NonNull
	private String usernameAssigned;
	
	@NonNull
	private Integer ticketsToProcess;
	
	@OneToMany
	private List<Ticket> ticketsProcessed = new ArrayList<Ticket>();
	
	@Enumerated(EnumType.ORDINAL)
	private ProcessStatus status = ProcessStatus.WORKING_IN_PROGRESS;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	public void addTicketProcessed(Ticket ticket) {
		this.ticketsProcessed.add(ticket);
	}
	
	public List<Ticket> getTicketsProcessed() {
		return Collections.unmodifiableList(ticketsProcessed);
	}
	
	public int getNumberTicketsProcessed() {
		return this.ticketsProcessed.size();
	}
	
	public void updateStatus() {
		this.status = ticketsToProcess.intValue() > getNumberTicketsProcessed() ?
				ProcessStatus.PARTIALLY_ATTENDED : ProcessStatus.TOTALLY_ATTENDED;
	}
}
