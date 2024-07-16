package com.example.todoRestfulService.Todo.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Long id) {
        super("Task not found with id: " + id);
    }
}
