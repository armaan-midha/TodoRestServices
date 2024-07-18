package com.example.todoRestfulService.Todo.controllers;

import com.example.todoRestfulService.Todo.dto.ApiResponse;
import com.example.todoRestfulService.Todo.models.TaskList;
import com.example.todoRestfulService.Todo.services.TaskListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/task_lists")
public class TaskListController {
    private static final Logger log = LoggerFactory.getLogger(TaskListController.class);
    private TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getAllTasksLists() {
        List<TaskList> lists = taskListService.findAll();
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTaskListById(@PathVariable Long id){
        Optional<TaskList> taskList = taskListService.findById(id);
        if(taskList.isPresent()) {
            ApiResponse apiResponse = new ApiResponse("Task List is present", HttpStatus.OK.value(), taskList);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }else{
            ApiResponse apiResponse = new ApiResponse("Task List is not present", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTaskList(@RequestBody TaskList taskList) {
        TaskList createdTaskList = taskListService.createTask(taskList);
        ApiResponse apiResponse = new ApiResponse("Task List Created Successfully", HttpStatus.CREATED.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTaskList(@PathVariable Long id) {
        Optional<TaskList> taskList = taskListService.findById(id);
        if(taskList.isPresent()){
            taskListService.deleteTask(taskList.get().getId());
            ApiResponse apiResponse = new ApiResponse("Task List Deleted Successfully", HttpStatus.NO_CONTENT.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        } else{
            ApiResponse response = new ApiResponse("Task List not found", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTaskList(@PathVariable Long id, @RequestBody TaskList taskListDetails){
        Optional<TaskList> optionalTaskList = taskListService.findById(id);
        if(optionalTaskList.isPresent()) {
            taskListService.updateTaskList(id, taskListDetails);
            ApiResponse apiResponse = new ApiResponse("Task List updated successfully", HttpStatus.OK.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }else{
            ApiResponse apiResponse = new ApiResponse("Task List is not present", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
