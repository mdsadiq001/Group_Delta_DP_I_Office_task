package main.java.services;

import main.java.models.Feedback;
import main.java.storage.FileManager;

import java.util.*;

public class FeedbackService {

    private final String FILE = "data/feedback.txt";

    public void addFeedback(Feedback fb) {

        String line = fb.getFeedbackId() + "," +
                fb.getEmployeeId() + "," +
                fb.getMessage() + "," +
                fb.getDate();

        FileManager.writeLine(FILE, line);
    }

    public List<Feedback> getFeedbackForEmployee(String empId) {

        List<String> lines = FileManager.readAll(FILE);
        List<Feedback> list = new ArrayList<>();

        for (String l : lines) {

            String[] t = l.split(",");

            if (t[1].equals(empId)) {

                list.add(new Feedback(
                        Integer.parseInt(t[0]),
                        t[1],
                        t[2],
                        t[3]
                ));
            }
        }

        return list;
    }

    public List<String> getFeedback(String empId) {

        List<String> all = FileManager.readAll(FILE);
        List<String> res = new ArrayList<>();

        for (String l : all) {
            if (l.contains(empId))
                res.add(l);
        }

        return res;
    }
}