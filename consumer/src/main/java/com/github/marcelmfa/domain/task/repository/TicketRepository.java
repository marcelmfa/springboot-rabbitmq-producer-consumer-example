package com.github.marcelmfa.domain.task.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.marcelmfa.domain.task.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
