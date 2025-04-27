package com.example.gestion_pharmacie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String login = loginField.getText();
        String password = passwordField.getText();

        if ("admin".equals(login) && "1234".equals(password)) {
            messageLabel.setText("Connexion réussie !");
        } else {
            messageLabel.setText("Login ou mot de passe incorrect.");
        }
    }
}
