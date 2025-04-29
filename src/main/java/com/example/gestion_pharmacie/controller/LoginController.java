package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;


public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView logoImage;

    @FXML
    public void initialize() {
        // Method 1: Load image from project resources
        Image logo = new Image(getClass().getResourceAsStream("/com/example/gestion_pharmacie/img/logo.png"));



        logoImage.setImage(logo);

        // Optional: If you want the image to be circular
        // Create a circle clip
        Circle clip = new Circle(40, 40, 40);
        logoImage.setClip(clip);
    }





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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/dashboard.fxml"));
            Parent root = loader.load();

            // Access the controller and set the user login
            DashboardController controller = loader.getController();
            controller.setUserLogin(loginField.getText());

            Stage stage = (Stage) loginField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Erreur lors du chargement du tableau de bord.");
        }
    }

}