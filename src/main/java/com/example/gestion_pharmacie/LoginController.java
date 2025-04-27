package com.example.gestion_pharmacie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private UserService userService = new UserService(); // make sure you have UserService ready!

    @FXML
    private void handleLogin(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs !");
            return;
        }

        if (userService.authenticateUser(login, password)) {
            messageLabel.setText("Connexion réussie !");
            goToDashboard();
        } else {
            messageLabel.setText("Login ou mot de passe incorrect !");
        }
    }

    private void goToDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) loginField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Erreur lors du chargement du tableau de bord.");
        }
    }
}
