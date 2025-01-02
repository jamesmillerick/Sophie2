package com.fyp.SpringSophie2.Service;

import com.fyp.SpringSophie2.Repository.TaskRepository;
import com.fyp.SpringSophie2.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

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
    public Optional<Task> getTaskById(int taskId) {
        return taskRepository.findById(taskId);
    }

    //Update an existing Task
    public Task updateTask(int taskId, Task updatedTask) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setTaskDescription(updatedTask.getTaskDescription());
                    task.setDueDate(updatedTask.getDueDate());
                    task.setEventStatus(updatedTask.getEventStatus());
                    task.setEventID(updatedTask.getEventID());
                    task.setAssignedEmployee(updatedTask.getAssignedEmployee());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));
    }

    // Delete a Task by its ID
    public void deleteTask(int taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }
}
