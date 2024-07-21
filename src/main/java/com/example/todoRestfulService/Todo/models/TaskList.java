package com.example.todoRestfulService.Todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name="task_list")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, message = "Title must have at least 2 characters")
    private String name;

    private String icon = "list";  // Default value

    private String iconColour = "#fff";

    private Boolean isDeletable = true;

    @JsonIgnore
    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public TaskList(){

    }

    public TaskList(Long id, String name, String icon, String iconColour, List<Task> tasks, Boolean isDeletable) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.iconColour = iconColour;
        this.isDeletable = isDeletable;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconColour() {
        return iconColour;
    }

    public void setIconColour(String iconColour) {
        this.iconColour = iconColour;
    }

    public Boolean getDeletable() {
        return isDeletable;
    }

    public void setDeletable(Boolean deletable) {
        isDeletable = deletable;
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", iconColour='" + iconColour + '\'' +
                ", isDeletable='" + isDeletable + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
