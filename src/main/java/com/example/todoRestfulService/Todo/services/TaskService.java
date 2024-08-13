package com.example.todoRestfulService.Todo.services;

import com.example.todoRestfulService.Todo.exceptions.NotFoundException;
import com.example.todoRestfulService.Todo.models.TaskList;
import com.example.todoRestfulService.Todo.repository.TaskRepository;
import com.example.todoRestfulService.Todo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private TaskListService taskListService;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskListService taskListService) {
        this.taskRepository = taskRepository;
        this.taskListService = taskListService;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByTaskListId(Long taskListId) {
        return taskRepository.findByTaskList_Id(taskListId);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Map<String, Object> taskMap) {
        Task task = new Task();

        Long taskListId = taskMap.get("taskListId") != null ? ((Number) taskMap.get("taskListId")).longValue() : null;
        setTaskList(task, taskListId);

        taskMap.forEach((key, value) -> {
            try {
                    Field field = Task.class.getDeclaredField(key);
                    field.setAccessible(true);

                    if (field.getType().equals(LocalDateTime.class) && value instanceof String) {
                        field.set(task, LocalDateTime.parse((String) value));
                    } else if (field.getType().equals(boolean.class) && value instanceof Boolean) {
                        field.set(task, (Boolean) value);
                    } else if (field.getType().equals(Long.class) && value instanceof Number) {
                        field.set(task, ((Number) value).longValue());
                    } else if (field.getType().equals(String.class) && value instanceof String) {
                        field.set(task, value);
                    }
            } catch (NoSuchFieldException | IllegalAccessException e) {
            }
        });

        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

public Task updateTask(Long id, Map<String, Object> taskDetailsMap) {
    Task task = taskRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(id));

    taskDetailsMap.forEach((key, value) -> {
        try {
            if ("taskListId".equals(key)) {
                Long taskListId = ((Number) value).longValue();
                setTaskList(task, taskListId);
            } else {
                Field field = Task.class.getDeclaredField(key);
                field.setAccessible(true);

                if (field.getType().equals(LocalDateTime.class) && value instanceof String) {
                    field.set(task, LocalDateTime.parse((String) value));
                } else if (field.getType().equals(boolean.class) && value instanceof Boolean) {
                    field.set(task, (Boolean) value);
                } else if (field.getType().equals(Long.class) && value instanceof Number) {
                    field.set(task, ((Number) value).longValue());
                } else if (field.getType().equals(String.class) && value instanceof String) {
                    field.set(task, value);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
    });

    return taskRepository.save(task);
}
    private void setTaskList(Task task, Long taskListId) {
        TaskList taskList;
        if (taskListId == null) {
            taskList = taskListService.findById(1L)
                    .orElseThrow(() -> new NotFoundException(1L));
        } else {
            taskList = taskListService.findById(taskListId)
                    .orElseThrow(() -> new NotFoundException(taskListId));
        }
        task.setTaskList(taskList);
    }
}
