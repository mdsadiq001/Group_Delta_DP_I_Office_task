package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.java.models.*;
import main.java.services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployerController {
    @FXML
    private ComboBox<String> empCombo;
    @FXML
    private ComboBox<String> fbEmpCombo;
    @FXML
    private AnchorPane assignBox;
    @FXML
    private AnchorPane feedbackBox;
    @FXML
    private TextArea outputArea;

    @FXML
    private TextField taskIdField, titleField, descField, empIdField, deadlineField;
    @FXML
    private TextField fbIdField, fbEmpField, msgField, dateField;
    @FXML private ComboBox<String> deleteTaskCombo;

    private TaskService taskService = new TaskService();
    private FeedbackService feedbackService = new FeedbackService();
    private Map<String, String> userMap = new HashMap<>();

    // ADD TASK
    @FXML
    public void addTask() {

        if (empCombo.getValue() == null) {
            outputArea.setText("Select an employee first!");
            return;
        }

        try {

            String username = empCombo.getValue();
            String empId = userMap.get(username);

            Task t = new Task(
                    Integer.parseInt(taskIdField.getText()),
                    titleField.getText(),
                    descField.getText(),
                    empId,   //  store ID
                    "Pending",
                    deadlineField.getText()
            );

            taskService.addTask(t);

            outputArea.setText("Task Added Successfully");

            clearTaskForm();

        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private void loadEmployees() {

        List<String> users = main.java.storage.FileManager.readAll("data/users.txt");

        for (String u : users) {

            String[] t = u.split(",");

            if (t[3].equalsIgnoreCase("employee")) {

                String id = t[0];
                String username = t[1];

                empCombo.getItems().add(username);
                fbEmpCombo.getItems().add(username);

                userMap.put(username, id); // key
            }
        }
    }

    @FXML
    public void initialize() {
        loadEmployees();
        loadDeleteTasks();
    }


    // VIEW PENDING
    @FXML
    public void showPending() {

        List<Task> tasks = taskService.getPendingTasks();

        StringBuilder sb = new StringBuilder();

        for (Task t : tasks) {

            sb.append("ID: ").append(t.getTaskId()).append("\n");
            sb.append("Title: ").append(t.getTitle()).append("\n");
            sb.append("Description: ").append(t.getDescription()).append("\n");
            sb.append("Status: ").append(t.getStatus()).append("\n");
            sb.append("Deadline: ").append(t.getDeadline()).append("\n");
            sb.append("----------------------------\n");
        }

        outputArea.setText(sb.toString());
    }

    // VIEW COMPLETED
    @FXML
    public void showCompleted() {

        List<Task> tasks = taskService.getCompletedTasks();

        StringBuilder sb = new StringBuilder();

        for (Task t : tasks) {

            sb.append("ID: ").append(t.getTaskId()).append("\n");
            sb.append("Title: ").append(t.getTitle()).append("\n");
            sb.append("Description: ").append(t.getDescription()).append("\n");
            sb.append("Status: ").append(t.getStatus()).append("\n");
            sb.append("Deadline: ").append(t.getDeadline()).append("\n");
            sb.append("----------------------------\n");
        }

        outputArea.setText(sb.toString());
    }

    // GIVE FEEDBACK
    @FXML
    public void giveFeedback() {

        if (fbEmpCombo.getValue() == null) {
            outputArea.setText("Select an employee first!");
            return;
        }

        try {

            String username = fbEmpCombo.getValue();
            String empId = userMap.get(username); //

            Feedback fb = new Feedback(
                    Integer.parseInt(fbIdField.getText()),
                    empId,   //  ID
                    msgField.getText(),
                    dateField.getText()
            );

            feedbackService.addFeedback(fb);

            outputArea.setText("Feedback Given Successfully");

            clearFeedbackForm();

        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    // Fills the dropdown with "ID - Title"
    private void loadDeleteTasks() {
        deleteTaskCombo.getItems().clear();
        List<Task> allTasks = taskService.getAllTasks();
        for (Task t : allTasks) {
            deleteTaskCombo.getItems().add(t.getTaskId() + " - " + t.getTitle());
        }
    }

    //del button
    @FXML
    public void handleDeleteTask() {
        if (deleteTaskCombo.getValue() == null) {
            outputArea.setText("Please select a task to delete!");
            return;
        }

        try {
            // iid+str
            String selected = deleteTaskCombo.getValue();
            int taskId = Integer.parseInt(selected.split(" - ")[0]);

            taskService.deleteTask(taskId);
            outputArea.setText("Task " + taskId + " deleted successfully!");

            loadDeleteTasks(); // Refresh
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private void clearFeedbackForm() {
        fbIdField.clear();
        msgField.clear();
        dateField.clear();
        fbEmpCombo.setValue(null);
    }
    private void clearTaskForm() {
        taskIdField.clear();
        titleField.clear();
        descField.clear();
        deadlineField.clear();
        empCombo.setValue(null);
    }

    // UI Switch
    @FXML
    public void showAssignForm() {
        assignBox.setVisible(true);
        feedbackBox.setVisible(false);
    }

    @FXML
    public void showFeedbackForm() {
        assignBox.setVisible(false);
        feedbackBox.setVisible(true);
    }

}