package main.java.controllers;

import main.java.services.TimerService;

public class TimerController {

    private TimerService service = new TimerService();

    public void startTimer(String empId) {
        service.startTimer(empId);
    }

    public void stopTimer(int taskId) {
        service.stopTimer(taskId);
    }
}


