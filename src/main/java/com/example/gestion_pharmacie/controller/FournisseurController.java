package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.model.Fournisseur;
import com.example.gestion_pharmacie.service.FournisseurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class FournisseurController {

    @FXML
    private TableView<Fournisseur> fournisseurTable;

    private ObservableList<Fournisseur> fournisseurList;
    private FournisseurService fournisseurService;

    public FournisseurController() {
        fournisseurService = new FournisseurService();
        fournisseurList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        try {
            loadFournisseurs();
        } catch (SQLException e) {
            showError("Erreur", "Erreur lors du chargement des fournisseurs.");
            e.printStackTrace();
        }
    }

    // Charger les fournisseurs dans la table
    public void loadFournisseurs() throws SQLException {
        fournisseurList.setAll(fournisseurService.getAllFournisseurs());
        fournisseurTable.setItems(fournisseurList);
    }

    // Ajouter un fournisseur par défaut (non utilisé ici, vous ouvrez un formulaire à la place)
    @FXML
    private void addFournisseur() {
        try {
            Fournisseur newFournisseur = new Fournisseur(0, "Nouveau Fournisseur", "Adresse", "0123456789");
            fournisseurService.addFournisseur(newFournisseur);
            loadFournisseurs();
            showInfo("Succès", "Fournisseur ajouté avec succès.");
        } catch (SQLException e) {
            showError("Erreur", "Erreur lors de l'ajout du fournisseur.");
            e.printStackTrace();
        }
    }

    // Gérer le clic sur le bouton "Ajouter"
    @FXML
    private void handleAddButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/fournisseurForm.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur du formulaire et lui passer la référence du contrôleur principal
            FournisseurFormController formController = loader.getController();
            formController.setStage(new Stage());
            formController.setFournisseurController(this);

            Scene scene = new Scene(root);
            Stage stage = formController.getStage(); // On utilise le même stage
            stage.setScene(scene);
            stage.setTitle("Ajouter un fournisseur");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Erreur", "Erreur lors de l'ouverture du formulaire.");
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
}
