package com.example.todoRestfulService.Todo.services;

import com.example.todoRestfulService.Todo.exceptions.NotFoundException;
import com.example.todoRestfulService.Todo.repository.TaskRepository;
import com.example.todoRestfulService.Todo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task taskDetails){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        if (taskDetails.getTitle() != null) {
            task.setTitle(taskDetails.getTitle());
        }
        if (taskDetails.getDescription() != null) {
            task.setDescription(taskDetails.getDescription());
        }
        if (taskDetails.getDueDate() != null) {
            task.setDueDate(taskDetails.getDueDate());
        }
        if (taskDetails.getReminder() != null) {
            task.setReminder(taskDetails.getReminder());
        }
        if (taskDetails.getIsRecurring() != task.getIsRecurring()) {
            task.setIsRecurring(taskDetails.getIsRecurring());
        }
        if (taskDetails.getRecurrencePattern() != null) {
            task.setRecurrencePattern(taskDetails.getRecurrencePattern());
        }

        return taskRepository.save(task);
    }
}
