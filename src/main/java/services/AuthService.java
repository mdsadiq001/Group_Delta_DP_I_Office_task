package main.java.services;

import main.java.models.User;
import main.java.storage.FileManager;

import java.util.List;

public class AuthService {

    private final String FILE = "data/users.txt";

    public User login(String username, String password) {

        List<String> users = FileManager.readAll(FILE);

        for (String u : users) {

            String[] data = u.split(",");

            if (data[1].equals(username) && data[2].equals(password)) {

                return new User(
                        data[0],
                        data[1],
                        data[2],
                        data[3]
                );
            }
        }

        return null;
    }
}