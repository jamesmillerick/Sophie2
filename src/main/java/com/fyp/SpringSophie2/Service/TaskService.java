package com.fyp.SpringSophie2.Service;

import com.fyp.SpringSophie2.Repository.TaskRepository;
import com.fyp.SpringSophie2.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Retrieve all Tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    //Create a new Task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Retrieve a Task by its ID
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    //Update an existing Task
    public void updateTask(Long taskId, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(taskId);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTaskName(updatedTask.getTaskName());
            task.setTaskDescription(updatedTask.getTaskDescription());
            task.setTaskStatus(updatedTask.getTaskStatus());
            task.setAssignedEmployee(updatedTask.getAssignedEmployee());
            task.setEvent(updatedTask.getEvent());
            taskRepository.save(task);
        }
    }



    // Delete a Task by its ID
    public void deleteTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    // Get all tasks assigned to the logged-in user
    public List<Task> getTasksByAssignedEmployeeUsername(String username) {
        return taskRepository.findByAssignedEmployeeUsername(username);
    }
}

/*
    //Update task Status
    public void updateTaskStatus(Long taskId, String taskStatus) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTaskStatus(taskStatus); // Update the task
            taskRepository.save(task); // Save updated task status to the database
        }
    }

     */
