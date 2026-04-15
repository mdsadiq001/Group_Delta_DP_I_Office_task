package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import main.java.models.User;
import main.java.services.AuthService;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private AuthService authService = new AuthService();

    @FXML
    public void handleLogin() {

        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = authService.login(username, password);

            if (user == null) {
                statusLabel.setText("Invalid username or password");
                return;
            }

            Stage stage = (Stage) usernameField.getScene().getWindow();

            //Employer dashboard
            if (user.getRole().equalsIgnoreCase("employer")) {

                Parent root = FXMLLoader.load(
                        getClass().getResource("/ui/employer.fxml")
                );

                stage.setScene(new Scene(root));
            }
            //Employee Dashboard
            else if (user.getRole().equalsIgnoreCase("employee")) {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/ui/employee.fxml")
                );

                Parent root = loader.load();

                EmployeeController controller = loader.getController();
                //controller.setEmployeeId(user.getId());
                controller.setUser(user);
                stage.setScene(new Scene(root));
            }

            else {
                statusLabel.setText("Wrong id or password");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}