package com.example.javaSpringCRUD.repo;
import java.util.*;
import org.springframework.stereotype.Repository;

import com.example.javaSpringCRUD.exceptions.CustomRunTimeException;
import com.example.javaSpringCRUD.model.Task;

import jakarta.annotation.PostConstruct;

@Repository
public class CrudRepo {
    // local list of all tasks
    private final List<Task> allTasks = new ArrayList<>();

    // returning all tasks
    public List<Task> findAllTasks(){
        return allTasks;
    }

    // post a new task
    public void addTask(Task newTask){
        allTasks.add(newTask);
    }

    // find task by id
    public Optional<Task> findById(Integer id){
        Optional<Task> foundTask = allTasks.stream().filter(currentTask -> currentTask.id() == id).findFirst();
        if(foundTask.isPresent()){
            return foundTask;
        }else{
            throw new CustomRunTimeException("Task Was Not Found My Boi... Better Luck Next Time");
        }
 
    }

    // update a single task
    public void updateTask(Integer id, Task newTask){
        Optional<Task> foundTask = findById(id);
        if (foundTask.isPresent()){
            Integer foundTaskIndex = allTasks.indexOf(foundTask.get());
            allTasks.set(foundTaskIndex, newTask);
        }else{
            throw new CustomRunTimeException("Cannot Update the task boi... your id is messed up");
        }
    }

    // deleting a task
    public void deleteTask(Integer id){
        allTasks.removeIf(foundTask -> foundTask.id().equals(id));
    }

    // temporary population of in memory tasks
    @PostConstruct
    private void addingDummyTasks(){
        int taskQuantity = 10;
        for (int i = 0; i < taskQuantity; i++){
            allTasks.add(new Task(i + 1, "random task", false, "debugging"));
        }
    }   
}
