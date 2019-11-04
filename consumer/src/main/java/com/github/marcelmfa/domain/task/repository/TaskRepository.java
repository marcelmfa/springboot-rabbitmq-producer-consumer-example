package com.github.marcelmfa.domain.task.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.marcelmfa.domain.task.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
