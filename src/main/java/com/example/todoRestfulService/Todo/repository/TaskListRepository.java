package com.example.todoRestfulService.Todo.repository;

import com.example.todoRestfulService.Todo.models.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

}
