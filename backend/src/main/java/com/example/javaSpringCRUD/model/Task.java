package com.example.javaSpringCRUD.model;

import jakarta.validation.constraints.NotEmpty;

// immutable entity since using records
public record Task(
    Integer id,
    @NotEmpty
    String taskTitle,
    boolean completed,
    String categoryTask
    
) {
    // external validations
    public Task{
        if (taskTitle.isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }else if(categoryTask.isEmpty()){
            throw new IllegalArgumentException("Task category cannot be empty");
        }
    }
}
