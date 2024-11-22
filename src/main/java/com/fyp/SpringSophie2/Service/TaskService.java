package com.fyp.SpringSophie2.Service;

import com.fyp.SpringSophie2.Repository.EventRepository;
import com.fyp.SpringSophie2.Repository.TaskRepository;

public class TaskService {

    private final EventRepository eventRepository;
    private final TaskRepository taskRepository;

    public TaskService(EventRepository eventRepository, TaskRepository taskRepository) {
        this.eventRepository = eventRepository;
        this.taskRepository = taskRepository;
    }
}
