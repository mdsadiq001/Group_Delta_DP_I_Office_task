package main.java.services;

import main.java.models.WorkTimer;

public class TimerService {

    private WorkTimer timer;
    private TaskService taskService = new TaskService();

    public void startTimer(String empId) {

        timer = new WorkTimer(empId);
        timer.startTimer();
    }

    public void stopTimer(int taskId) {

        timer.stopTimer();

        String duration = timer.getFormattedDuration();

        taskService.markTaskComplete(taskId, duration);

        System.out.println("Task completed. Duration: " + duration);
    }
}