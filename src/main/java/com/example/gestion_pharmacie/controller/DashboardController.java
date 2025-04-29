package com.example.gestion_pharmacie.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button fournisseurButton;

    @FXML
    public void initialize() {
        fournisseurButton.setOnAction(this::handleFournisseurButton);
    }
    @FXML
    private void handleFournisseurButton(ActionEvent event) {
        try {
            // Load the fournisseurs interface (replace 'fournisseurs.fxml' with the correct name of your fxml file)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/fournisseur.fxml"));
            Parent root = loader.load();

            // Create new stage (window)
            Stage stage = new Stage();
            stage.setTitle("Gestion des Fournisseurs");
            stage.setScene(new Scene(root));
            stage.show();

            // Optionally, close the current dashboard window:
            Stage currentStage = (Stage) fournisseurButton.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
