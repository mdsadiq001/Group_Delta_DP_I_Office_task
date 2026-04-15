package main.java.models;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class WorkTimer implements Serializable{
    private static final long serialVersionUID = 1L;

    private String employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long durationInSeconds;

    public WorkTimer(String employeeId){
        this.employeeId = employeeId;
    }

    public void startTimer(){
        this.startTime = LocalDateTime.now();
        System.out.println("Time started for " + this.employeeId + " at " + this.startTime);
    }

    public void stopTimer(){
        if(startTime == null){
            System.out.println("Timer has never started.");
            return;
        }

        this.endTime = LocalDateTime.now();
        calculateDuration();
    }

    public void calculateDuration(){
        if(startTime != null && endTime != null){
            Duration diff = Duration.between(startTime, endTime);
            this.durationInSeconds = diff.getSeconds();
        }
    }

    public String getFormattedDuration(){
        long s = durationInSeconds;
        long hours = s / 3600;
        long minutes = (s % 3600) / 60;
        long seconds = s % 60;

        return String.format("%d Hours, %d Minutes, %d Seconds",
                hours, minutes, seconds);
    }

    public LocalDateTime getStartTime(){ 
      return this.startTime; 
    }
    public LocalDateTime getEndTime(){ 
      return this.endTime; 
    }
    public String getEmployeeId(){
      return this.employeeId; 
    }
      
}
