package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.model.Fournisseur;
import com.example.gestion_pharmacie.service.FournisseurService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FournisseurFormController {

    @FXML private TextField nomField;
    @FXML private TextField adresseField;
    @FXML private TextField telephoneField;

    private Stage stage;
    private FournisseurService fournisseurService;
    private FournisseurController fournisseurController; // New

    public FournisseurFormController() {
        this.fournisseurService = new FournisseurService();
    }

    @FXML
    private void handleSubmit() {
        String nom = nomField.getText();
        String adresse = adresseField.getText();
        String telephone = telephoneField.getText();

        if (nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty()) {
            showError("Erreur", "Tous les champs doivent être remplis.");
            return;
        }

        try {
            Fournisseur newFournisseur = new Fournisseur(0, nom, adresse, telephone);
            fournisseurService.addFournisseur(newFournisseur);

            // Refresh the list after adding
            if (fournisseurController != null) {
                fournisseurController.loadFournisseurs();
            }

            showInfo("Succès", "Fournisseur ajouté avec succès.");
            closeForm();
        } catch (SQLException e) {
            showError("Erreur", "Erreur lors de l'ajout du fournisseur.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        closeForm();
    }

    private void closeForm() {
        if (stage != null) {
            stage.close();
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setFournisseurController(FournisseurController fournisseurController) {
        this.fournisseurController = fournisseurController;
    }
}
