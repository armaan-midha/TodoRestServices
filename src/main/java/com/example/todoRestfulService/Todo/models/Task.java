package com.example.todoRestfulService.Todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, message = "Title must have at least 2 characters")
    @Column(name = "title", nullable = false)
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime reminder;

    @Column(nullable = false)
    private  boolean isDone = false;
    private boolean isRecurring;
    private String recurrencePattern;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id", nullable = true)
    @JsonIgnore
    private TaskList taskList;

    public Task() {
    }


    public Task(Long id, String title, String description, LocalDateTime dueDate, LocalDateTime reminder, boolean isRecurring, String recurrencePattern, LocalDateTime createdAt, LocalDateTime updatedAt, TaskList taskList, boolean isDone) {
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
        this.isDone = isDone;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", reminder=" + reminder +
                ", isDone=" + isDone +
                ", isRecurring=" + isRecurring +
                ", recurrencePattern='" + recurrencePattern + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", taskList=" + taskList +
                '}';
    }
}
