package com.example.todoRestfulService.Todo.repository;

import com.example.todoRestfulService.Todo.models.Task;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
