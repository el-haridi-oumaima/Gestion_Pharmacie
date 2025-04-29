package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.model.Fournisseur;
import com.example.gestion_pharmacie.service.FournisseurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class FournisseurController {
    @FXML
    private TableView<Fournisseur> fournisseurTable;

    @FXML
    private TableColumn<Fournisseur, Integer> idColumn;

    @FXML
    private TableColumn<Fournisseur, String> nameColumn;

    @FXML
    private TableColumn<Fournisseur, String> addressColumn;

    @FXML
    private TableColumn<Fournisseur, String> phoneColumn;

    @FXML
    private TableColumn<Fournisseur, Void> actionColumn;

    private ObservableList<Fournisseur> fournisseurList;
    private FournisseurService fournisseurService;

    public FournisseurController() {
        fournisseurService = new FournisseurService();
        fournisseurList = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        // Initialize the table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idFournisseur"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        // Setup the action column with Edit and Delete buttons
        setupActionColumn();

        try {
            loadFournisseurs();
        } catch (SQLException e) {
            showError("Erreur", "Erreur lors du chargement des fournisseurs.");
            e.printStackTrace();
        }
    }

    private void setupActionColumn() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Modifier");
            private final Button deleteButton = new Button("Supprimer");
            private final HBox buttonBox = new HBox(5, editButton, deleteButton);

            {
                // Style the buttons
                editButton.getStyleClass().add("edit-button");
                deleteButton.getStyleClass().add("delete-button");
                buttonBox.setAlignment(Pos.CENTER);

                // Set action for Edit button
                editButton.setOnAction(event -> {
                    Fournisseur fournisseur = getTableView().getItems().get(getIndex());
                    openEditForm(fournisseur);
                });

                // Set action for Delete button
                deleteButton.setOnAction(event -> {
                    Fournisseur fournisseur = getTableView().getItems().get(getIndex());
                    deleteFournisseur(fournisseur);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonBox);
                }
            }
        });
    }

    // Charger les fournisseurs dans la table
    public void loadFournisseurs() throws SQLException {
        fournisseurList.clear(); // Clear the list before adding new items
        fournisseurList.addAll(fournisseurService.getAllFournisseurs());
        fournisseurTable.setItems(fournisseurList);

        // Debug - print number of fournisseurs loaded
        System.out.println("Loaded " + fournisseurList.size() + " fournisseurs");
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

    // Method to open the edit form with pre-filled data
    private void openEditForm(Fournisseur fournisseur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/fournisseurForm.fxml"));
            Parent root = loader.load();

            // Get the controller and set it up for editing
            FournisseurFormController formController = loader.getController();
            formController.setStage(new Stage());
            formController.setFournisseurController(this);
            formController.setEditMode(fournisseur); // Set the form to edit mode with the selected fournisseur

            Stage stage = formController.getStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier un fournisseur");
            stage.initModality(Modality.APPLICATION_MODAL); // Make the dialog modal
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showError("Erreur", "Erreur lors de l'ouverture du formulaire de modification.");
        }
    }

    // Method to delete a fournisseur with confirmation
    private void deleteFournisseur(Fournisseur fournisseur) {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation de suppression");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("Êtes-vous sûr de vouloir supprimer le fournisseur " + fournisseur.getNom() + " ?");

        confirmDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    fournisseurService.deleteFournisseur(fournisseur.getIdFournisseur());
                    loadFournisseurs(); // Refresh the table
                    showInfo("Succès", "Fournisseur supprimé avec succès.");
                } catch (SQLException e) {
                    showError("Erreur", "Erreur lors de la suppression du fournisseur.");
                    e.printStackTrace();
                }
            }
        });
    }


    @FXML

    private Button btnRetourDashboard;

    @FXML
    private void handleRetourDashboard() {
        try {
            // Charger la vue du dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/dashboard.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène et l'afficher
            Stage stage = (Stage) btnRetourDashboard.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Erreur", "Erreur lors du retour au dashboard.");
        }
    }

}