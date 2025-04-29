package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.model.Fournisseur;
import com.example.gestion_pharmacie.service.FournisseurService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class FournisseurFormController {

    @FXML private TextField nomField;
    @FXML private TextField adresseField;
    @FXML private TextField telephoneField;
    @FXML private Button submitButton;

    private Stage stage;
    private FournisseurService fournisseurService;
    private FournisseurController fournisseurController;
    private Fournisseur currentFournisseur; // For edit mode
    private boolean editMode = false;

    public FournisseurFormController() {
        this.fournisseurService = new FournisseurService();
    }

    @FXML
    private void initialize() {
        // Any initialization if needed
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
            if (editMode && currentFournisseur != null) {
                // Update existing fournisseur
                currentFournisseur.setNom(nom);
                currentFournisseur.setAdresse(adresse);
                currentFournisseur.setTelephone(telephone);
                fournisseurService.updateFournisseur(currentFournisseur);
                showInfo("Succès", "Fournisseur modifié avec succès.");
            } else {
                // Add new fournisseur
                Fournisseur newFournisseur = new Fournisseur(0, nom, adresse, telephone);
                fournisseurService.addFournisseur(newFournisseur);
                showInfo("Succès", "Fournisseur ajouté avec succès.");
            }

            // Refresh the list after adding/updating
            if (fournisseurController != null) {
                fournisseurController.loadFournisseurs();
            }

            closeForm();
        } catch (SQLException e) {
            String action = editMode ? "la modification" : "l'ajout";
            showError("Erreur", "Erreur lors de " + action + " du fournisseur.");
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

    // Set the form to edit mode with pre-filled data
    public void setEditMode(Fournisseur fournisseur) {
        this.editMode = true;
        this.currentFournisseur = fournisseur;

        // Pre-fill the form fields with the fournisseur's data
        nomField.setText(fournisseur.getNom());
        adresseField.setText(fournisseur.getAdresse());
        telephoneField.setText(fournisseur.getTelephone());

        // Change the submit button text to "Modifier"
        submitButton.setText("Modifier");
    }
}