package main.java.controllers;

import main.java.models.Feedback;
import main.java.services.FeedbackService;
import main.java.storage.FileManager;

import java.util.ArrayList;
import java.util.List;

public class FeedbackController {

    private FeedbackService service = new FeedbackService();

    public void giveFeedback(Feedback fb) {

        service.addFeedback(fb);
    }


    public void viewFeedback(String empId) {

        List<Feedback> list = service.getFeedbackForEmployee(empId);

        for (Feedback f : list)
            System.out.println(f.getMessage() + " | " + f.getDate());
    }
}