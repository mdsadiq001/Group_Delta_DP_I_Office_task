package main.java.models;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private String employeeId;
    private String status;
    private String deadline;
    
    public Task(int taskId, String title, String description, String employeeId, String status, String deadline){
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.employeeId = employeeId;
        this.status = status;
        this.deadline = deadline;
    }
    
    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String toString(){
        return("Task ID: " + this.getTaskId() + "\nTitle: " + this.getTitle() + "\nDescription: " + this.getDescription() + "\nEmployee ID: " + this.getEmployeeId() + "\nStatus: " + this.getStatus() + "\nDeadline: " + this.getDeadline() + "\n\n" );

    }

}
