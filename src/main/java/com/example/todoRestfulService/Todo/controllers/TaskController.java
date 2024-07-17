package com.example.todoRestfulService.Todo.controllers;

import com.example.todoRestfulService.Todo.dto.ApiResponse;
import com.example.todoRestfulService.Todo.services.TaskService;
import com.example.todoRestfulService.Todo.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable Long id){
        Optional<Task> task = taskService.findById(id);
        if(task.isPresent()) {
            ApiResponse apiResponse = new ApiResponse("Task is present", HttpStatus.OK.value(), task);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }else{
            ApiResponse apiResponse = new ApiResponse("Task is not present", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTask(@RequestBody Map<String, Object> taskMap) {
        Task createdTask = taskService.createTask(taskMap);
        ApiResponse apiResponse = new ApiResponse("Task Created Successfully", HttpStatus.CREATED.value(), createdTask);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskService.findById(id);
        if(task.isPresent()){
            taskService.deleteTask(task.get().getId());
            ApiResponse apiResponse = new ApiResponse("Task Deleted Successfully", HttpStatus.NO_CONTENT.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        } else{
            ApiResponse response = new ApiResponse("Task not found", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable Long id, @RequestBody Map<String, Object> taskDetailsMap){
        Optional<Task> optionalTask = taskService.findById(id);
        if(optionalTask.isPresent()) {
            taskService.updateTask(id, taskDetailsMap);
            ApiResponse apiResponse = new ApiResponse("Task updated successfully", HttpStatus.OK.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }else{
            ApiResponse apiResponse = new ApiResponse("Task is not present", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
