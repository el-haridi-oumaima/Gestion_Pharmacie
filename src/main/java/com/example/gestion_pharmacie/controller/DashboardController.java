package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.dao.StockDAO;
import com.example.gestion_pharmacie.model.Stock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DashboardController {

    @FXML
    private Button fournisseurButton;

    @FXML
    private Button stockButton;

    @FXML
    private Button logoutButton; // Add this field to reference the logout button

    @FXML
    public void initialize() {
        fournisseurButton.setOnAction(this::handleFournisseurButton);
<<<<<<< HEAD
        // If you prefer to wire up the logout button here instead of in FXML:
        // logoutButton.setOnAction(this::handleLogout);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            // Load the login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/login.fxml"));
            Parent root = loader.load();

            // Get the current stage from the event source
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the login scene and show it
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Login");
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // You might want to display an error message to the user
        }
    }
=======
        afficherAlertesStocksFaibles();
    }



>>>>>>> devSalama

    @FXML
    private void handleFournisseurButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/fournisseur.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Gestion des Fournisseurs");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) fournisseurButton.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStockButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/StockView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Gestion du Stock");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) stockButton.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
}
=======

    @FXML
    private VBox lowStockBox;

    private final StockDAO stockDAO = new StockDAO();

    public void afficherAlertesStocksFaibles() {
        List<Stock> stocksFaibles = stockDAO.recupererStocksFaibles();

        for (Stock stock : stocksFaibles) {
            Label alert = new Label("⚠️ Le stock du médicament \"" + stock.getNomMedicament() + "\" est bas (quantité : " + stock.getQuantite() + ")");
            alert.getStyleClass().add("alert-label");
            lowStockBox.getChildren().add(alert);
        }
    }
}
>>>>>>> devSalama
