package com.example.todoRestfulService.Todo.DataInitializer;

import com.example.todoRestfulService.Todo.models.TaskList;
import com.example.todoRestfulService.Todo.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public void run(String... args) throws Exception {
        if (taskListRepository.count() == 0) {
            TaskList taskList1 = new TaskList(1L, "My Day", "sun", "#686D76", null, false);
            TaskList taskList2 = new TaskList(2L, "Important", "star", "#F4CE14", null, false );
            TaskList taskList3 = new TaskList(3L, "Planned", "calendar", "#CBFFA9" , null, false);
            TaskList taskList4 = new TaskList(4L, "Tasks", "home", "#C8ACD6", null, false);
            TaskList taskList5 = new TaskList(5L, "Flagged", "flag", "#FF8989", null, false);

            taskListRepository.saveAll(Arrays.asList(taskList1, taskList2, taskList3, taskList4, taskList5));
        }
    }
}
