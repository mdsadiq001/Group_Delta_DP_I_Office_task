package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import main.java.models.Task;
import main.java.models.User;
import main.java.services.*;

import java.util.List;

public class EmployeeController {
    private User currentUser;
    @FXML private ComboBox<String> taskCombo;
    @FXML private TextArea outputArea;
    @FXML private Label dashboardLabel;

    private TaskService taskService = new TaskService();
    private FeedbackService feedbackService = new FeedbackService();
    private TimerService timerService = new TimerService();

    private String currentEmployeeId;


    public void setUser(User user) {
        this.currentUser = user;

        //  set id
        this.currentEmployeeId = user.getId();

        //  set dashboard name
        String name = user.getUsername();
        name = name.substring(0,1).toUpperCase() + name.substring(1);

        dashboardLabel.setText(name + "'s Dashboard");

        loadTasks();
    }

    // Load tasks
    private void loadTasks() {

        taskCombo.getItems().clear();

        List<Task> tasks = taskService.getTasksByEmployee(currentEmployeeId);

        for (Task t : tasks) {
            if (t.getStatus().equalsIgnoreCase("Pending")) {
                taskCombo.getItems().add(
                        t.getTaskId() + " - " + t.getTitle()
                );
            }
        }
    }

    // view tasks
    @FXML
    public void viewTasks() {

        List<Task> tasks = taskService.getTasksByEmployee(currentEmployeeId);

        StringBuilder sb = new StringBuilder();

        for (Task t : tasks) {
            sb.append(t.toString()).append("\n");
        }

        outputArea.setText(sb.toString());
    }

    // view feedback
    @FXML
    public void viewFeedback() {

        List<String> list = feedbackService.getFeedback(currentEmployeeId);

        StringBuilder sb = new StringBuilder();

        for (String f : list) {
            sb.append(f).append("\n");
        }

        outputArea.setText(sb.toString());
    }

    // Start timer
    @FXML
    public void startTimer() {

        if (taskCombo.getValue() == null) {
            outputArea.setText("Select a task first!");
            return;
        }

        timerService.startTimer(currentEmployeeId);

        outputArea.setText("Timer Started...");
    }

    // stop timer and also complete the task
    @FXML
    public void stopTimer() {

        if (taskCombo.getValue() == null) {
            outputArea.setText("Select a task first!");
            return;
        }

        String selected = taskCombo.getValue();
        int taskId = Integer.parseInt(selected.split(" - ")[0]);

        timerService.stopTimer(taskId);

        outputArea.setText("Task completed!");

        loadTasks(); // refresh
    }

}