package com.github.marcelmfa.domain.task;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;

@Getter
@Entity
public class Ticket extends AbstractPersistable<Long> {

	@CreatedDate
	private LocalDateTime createdAt;
}
