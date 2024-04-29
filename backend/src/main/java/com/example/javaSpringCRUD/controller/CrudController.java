package com.example.javaSpringCRUD.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.javaSpringCRUD.exceptions.CustomRunTimeException;
import com.example.javaSpringCRUD.model.Task;
import com.example.javaSpringCRUD.repo.CrudRepo;

import jakarta.validation.Valid;

import java.util.*;

// will contain basic paths and rest controller for crud operations
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/tasks")
public class CrudController {

    // instantiating of the crud repo
    private final CrudRepo crudRepo;
    public CrudController(CrudRepo crudRepo){
        this.crudRepo = crudRepo;
    }

    // find by id check for task
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/{id}")
    Task foundTask(@PathVariable Integer id){
        Optional<Task> foundTask = this.crudRepo.findById(id);
        if (foundTask.isPresent()){
            return foundTask.get();
        }else{
            throw new CustomRunTimeException("Task is not present");
        }
    }

    // add a new task
    @PostMapping("/post")
    void addNewTask(@Valid @RequestBody Task newTask){
        this.crudRepo.addTask(newTask);
    }
    
    // getting the tasks
    @GetMapping("")
    List<Task> getTasks(){
        return this.crudRepo.findAllTasks();
    }   

    // deleting a task
    @DeleteMapping("/delete/{id}")
    void deleteTask(@PathVariable Integer id){
        this.crudRepo.deleteTask(id);
    }

    // updating a task
    @PutMapping("/update/{id}")
    void updateTask(@RequestBody Task newTask, @PathVariable Integer id){
        this.crudRepo.updateTask(id, newTask);
    }

}
