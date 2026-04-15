package main.java.models;

public class Feedback {
    private int feedbackId; //potential bug could arise
    private String employeeId;
    private String message;
    private String date;

    public Feedback(int feedbackId, String employeeId, String message, String date){
        this.feedbackId = feedbackId;
        this.employeeId = employeeId;
        this.message = message;
        this.date = date;

    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
