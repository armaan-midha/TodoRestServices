package com.example.todoRestfulService.Todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime reminder;
    private boolean isRecurring;
    private String recurrencePattern;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id", nullable = true)
    @JsonIgnore
    private TaskList taskList;

    public Task() {
    }


    public Task(Long id, String title, String description, LocalDateTime dueDate, LocalDateTime reminder, boolean isRecurring, String recurrencePattern, LocalDateTime createdAt, LocalDateTime updatedAt, TaskList taskList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.reminder = reminder;
        this.isRecurring = isRecurring;
        this.recurrencePattern = recurrencePattern;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.taskList = taskList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReminder() {
        return reminder;
    }

    public void setReminder(LocalDateTime reminder) {
        this.reminder = reminder;
    }

    public boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", reminder=" + reminder +
                ", isRecurring=" + isRecurring +
                ", recurrencePattern='" + recurrencePattern + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", taskList=" + taskList +
                '}';
    }
}
