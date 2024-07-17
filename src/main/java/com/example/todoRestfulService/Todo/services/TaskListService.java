package com.example.todoRestfulService.Todo.services;

import com.example.todoRestfulService.Todo.exceptions.NotFoundException;
import com.example.todoRestfulService.Todo.models.Task;
import com.example.todoRestfulService.Todo.models.TaskList;
import com.example.todoRestfulService.Todo.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {

    private TaskListRepository taskListRepository;

    @Autowired
    public TaskListService(TaskListRepository taskListRepository){
        this.taskListRepository = taskListRepository;
    }

    public List<TaskList> findAll(){
        return taskListRepository.findAll();
    }
    public Optional<TaskList> findById(Long id) {
        return taskListRepository.findById(id);
    }

    public TaskList createTask(TaskList taskList){
        return taskListRepository.save(taskList);
    }

    public void deleteTask(Long id){
        taskListRepository.deleteById(id);
    }

    public TaskList updateTaskList(Long id, TaskList taskListDetails){

        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        if (taskListDetails.getName() != null) {
            taskList.setName(taskListDetails.getName());
        }

        return taskListRepository.save(taskList);
    }
}
