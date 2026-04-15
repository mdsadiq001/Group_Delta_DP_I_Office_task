package main.java.controllers;

import main.java.models.Task;
import main.java.services.TaskService;

import java.util.List;

public class TaskController {

    private TaskService service = new TaskService();

    public void assignTask(Task task) {

        service.addTask(task);
    }

    public void viewTasks(String empId) {

        List<Task> tasks = service.getTasksByEmployee(empId);

        for (Task t : tasks)
            System.out.println(t);
    }

    public void viewPendingTasks() {

        List<Task> tasks = service.getPendingTasks();

        for (Task t : tasks)
            System.out.println(t);
    }

    public void viewCompletedTasks() {

        List<Task> tasks = service.getCompletedTasks();

        for (Task t : tasks)
            System.out.println(t);
    }

    public void completeTask(int taskId, String duration) {

        service.markTaskComplete(taskId, duration);
        System.out.println("Task marked as completed");
    }
}