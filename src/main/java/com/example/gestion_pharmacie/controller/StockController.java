package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.dao.StockDAO;
import com.example.gestion_pharmacie.dao.FournisseurDAO;
import com.example.gestion_pharmacie.model.Stock;
import com.example.gestion_pharmacie.model.Fournisseur;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class StockController {

    @FXML
    private TableView<Stock> tableStock;

    @FXML
    private TableColumn<Stock, String> colMedicament;

    @FXML
    private TableColumn<Stock, String> colFournisseur;

    @FXML
    private TableColumn<Stock, Double> colPrix;

    @FXML
    private TableColumn<Stock, LocalDate> colDateEntree;

    @FXML
    private TableColumn<Stock, Integer> colQuantite;

    @FXML
    private TableColumn<Stock, Void> colActions;

    @FXML
    private Button btnAjouter;

    private final StockDAO stockDAO = new StockDAO();
    private final FournisseurDAO fournisseurDAO = new FournisseurDAO();
    private ObservableList<Stock> stockList;
    private ObservableList<Fournisseur> fournisseurList;

    @FXML
    public void initialize() {
        stockList = FXCollections.observableArrayList();
        loadFournisseurs();

        // Configure table columns
        colMedicament.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomMedicament()));
        colFournisseur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFournisseurNom()));
        colPrix.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrix()).asObject());
        colDateEntree.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateEntree()));
        colQuantite.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject());

        // Format date column
        colDateEntree.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setText((empty || date == null) ? null : formatter.format(date));
            }
        });

        // Add buttons to actions column
        ajouterColonneActions();

        // Load data from database
        chargerDonnees();
    }

    private void loadFournisseurs() {
        try {
            List<Fournisseur> fournisseurs = fournisseurDAO.getAllFournisseurs();
            fournisseurList = FXCollections.observableArrayList(fournisseurs);
        } catch (SQLException e) {
            afficherErreur("Erreur lors du chargement des fournisseurs", e);
        }
    }

    private void chargerDonnees() {
        stockList.setAll(stockDAO.recupererTousLesStocks());
        tableStock.setItems(stockList);
    }

    private void ajouterColonneActions() {
        colActions.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Stock, Void> call(final TableColumn<Stock, Void> param) {
                return new TableCell<>() {
                    private final Button editButton = new Button("Modifier");
                    private final Button deleteButton = new Button("Supprimer");
                    private final HBox pane = new HBox(5, editButton, deleteButton);

                    {
                        editButton.setOnAction(event -> {
                            Stock stock = getTableRow().getItem();
                            if (stock != null) ouvrirDialogModification(stock);
                        });

                        deleteButton.setOnAction(event -> {
                            Stock stock = getTableRow().getItem();
                            if (stock != null) ouvrirDialogSuppression(stock);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : pane);
                    }
                };
            }
        });
    }

    @FXML
    private void handleAjouterStock() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/StockDialog.fxml"));
            DialogPane dialogPane = loader.load();

            StockDialogController controller = loader.getController();
            controller.setModeAjout();

            // The StockDialogController will load fournisseurs automatically in its initialize method

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Ajouter un stock");

            Optional<ButtonType> clickedButton = dialog.showAndWait();

            if (clickedButton.isPresent() && clickedButton.get() == ButtonType.OK) {
                Stock nouveauStock = controller.getStock();
                stockDAO.ajouterStock(nouveauStock);
                chargerDonnees();
            }
        } catch (IOException e) {
            afficherErreur("Erreur lors de l'ouverture du dialogue d'ajout", e);
        } catch (Exception e) {
            afficherErreur("Une erreur inattendue s'est produite", e);
        }
    }

    private void ouvrirDialogModification(Stock stock) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/StockDialog.fxml"));
            DialogPane dialogPane = loader.load();

            StockDialogController controller = loader.getController();
            controller.setStock(stock);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Modifier un stock");

            Optional<ButtonType> clickedButton = dialog.showAndWait();

            if (clickedButton.isPresent() && clickedButton.get() == ButtonType.OK) {
                Stock stockModifie = controller.getStock();
                stockDAO.modifierStock(stockModifie);
                chargerDonnees();
            }
        } catch (IOException e) {
            afficherErreur("Erreur lors de l'ouverture du dialogue de modification", e);
        }
    }

    private void ouvrirDialogSuppression(Stock stock) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestion_pharmacie/views/SuppressionDialog.fxml"));
            DialogPane dialogPane = loader.load();

            Label lblMedicament = (Label) dialogPane.lookup("#lblMedicament");
            lblMedicament.setText(stock.getNomMedicament());

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Supprimer un stock");

            Optional<ButtonType> clickedButton = dialog.showAndWait();

            if (clickedButton.isPresent() && clickedButton.get() == ButtonType.YES) {
                stockDAO.supprimerStock(stock.getNomMedicament());
                chargerDonnees();
            }
        } catch (IOException e) {
            afficherErreur("Erreur lors de l'ouverture du dialogue de suppression", e);
        }
    }

    private void afficherErreur(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}