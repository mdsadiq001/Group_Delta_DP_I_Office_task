package main.java.services;

import main.java.models.Task;
import main.java.storage.FileManager;

import java.util.*;

public class TaskService {

    private final String FILE = "data/tasks.txt";

    public void addTask(Task task) {

        String line =
                task.getTaskId() + "," +
                        task.getTitle() + "," +
                        task.getDescription() + "," +
                        task.getEmployeeId() + "," +
                        task.getStatus() + "," +
                        task.getDeadline() + "," +
                        "0";

        FileManager.writeLine(FILE, line);
    }

    public List<Task> getTasksByEmployee(String empId) {

        List<String> lines = FileManager.readAll(FILE);
        List<Task> tasks = new ArrayList<>();

        for (String l : lines) {

            String[] t = l.split(",");

            if (t[3].equals(empId)) {

                tasks.add(
                        new Task(
                                Integer.parseInt(t[0]),
                                t[1],
                                t[2],
                                t[3],
                                t[4],
                                t[5]
                        )
                );
            }
        }

        return tasks;
    }

    public void markTaskComplete(int taskId, String duration) {

        List<String> lines = FileManager.readAll(FILE);
        List<String> updated = new ArrayList<>();

        for (String l : lines) {

            String[] t = l.split(",");

            if (Integer.parseInt(t[0]) == taskId) {

                t[4] = "Completed";

                if (t.length < 7)
                    updated.add(String.join(",", t) + "," + duration);
                else {
                    t[6] = duration;
                    updated.add(String.join(",", t));
                }

            } else {

                updated.add(l);
            }
        }

        FileManager.writeAll(FILE, updated);
    }

    public List<Task> getPendingTasks() {

        List<String> lines = FileManager.readAll(FILE);
        List<Task> tasks = new ArrayList<>();

        for (String l : lines) {

            String[] t = l.split(",");

            if (t[4].equals("Pending")) {

                tasks.add(
                        new Task(
                                Integer.parseInt(t[0]),
                                t[1],
                                t[2],
                                t[3],
                                t[4],
                                t[5]
                        )
                );
            }
        }

        return tasks;
    }

    public List<Task> getCompletedTasks() {

        List<String> lines = FileManager.readAll(FILE);
        List<Task> tasks = new ArrayList<>();

        for (String l : lines) {

            String[] t = l.split(",");

            if (t[4].equals("Completed")) {

                tasks.add(
                        new Task(
                                Integer.parseInt(t[0]),
                                t[1],
                                t[2],
                                t[3],
                                t[4],
                                t[5]
                        )
                );
            }
        }

        return tasks;
    }

    public void deleteTask(int taskId) {
        List<String> lines = FileManager.readAll(FILE);
        List<String> updated = new ArrayList<>();

        for (String l : lines) {
            String[] t = l.split(",");
            // Only keep
            if (Integer.parseInt(t[0]) != taskId) {
                updated.add(l);
            }
        }
        // Overwrite
        FileManager.writeAll(FILE, updated);
    }

    // Helper method to get all tasks for the deletion dropdown
    public List<Task> getAllTasks() {
        List<String> lines = FileManager.readAll(FILE);
        List<Task> tasks = new ArrayList<>();
        for (String l : lines) {
            String[] t = l.split(",");
            // Creates Task obj
            tasks.add(new Task(
                    Integer.parseInt(t[0]), t[1], t[2], t[3], t[4], t[5]
            ));
        }
        return tasks;
    }

}