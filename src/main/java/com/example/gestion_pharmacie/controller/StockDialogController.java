package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.model.Fournisseur;
import com.example.gestion_pharmacie.model.Stock;
import com.example.gestion_pharmacie.service.FournisseurService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StockDialogController {

    @FXML
    private TextField txtNomMedicament;

    @FXML
    private ComboBox<Fournisseur> cmbFournisseur;

    @FXML
    private TextField txtPrix;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField txtQuantite;

    private Stock stock;
    private boolean isAddMode = true;
    private final FournisseurService fournisseurService = new FournisseurService();

    @FXML
    public void initialize() {
        // Set today's date as default for datePicker
        datePicker.setValue(LocalDate.now());

        // Configure the ComboBox to display Fournisseur names
        configureComboBox();

        // Load all fournisseurs into the ComboBox
        loadFournisseurs();
    }

    private void configureComboBox() {
        // Set the cell factory to display only the fournisseur name in the dropdown list
        cmbFournisseur.setCellFactory(lv -> new ListCell<Fournisseur>() {
            @Override
            protected void updateItem(Fournisseur item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNom());
            }
        });

        // Set converter to display and use the Fournisseur name in the ComboBox
        cmbFournisseur.setConverter(new StringConverter<Fournisseur>() {
            @Override
            public String toString(Fournisseur fournisseur) {
                return fournisseur == null ? null : fournisseur.getNom();
            }

            @Override
            public Fournisseur fromString(String string) {
                // This method is called when the user types in the ComboBox
                // We don't need to implement it for our purpose
                return null;
            }
        });
    }

    private void loadFournisseurs() {
        try {
            // Get all fournisseurs from the service
            List<Fournisseur> fournisseurs = fournisseurService.getAllFournisseurs();

            // Convert to ObservableList and set as items in the ComboBox
            ObservableList<Fournisseur> fournisseursList = FXCollections.observableArrayList(fournisseurs);
            cmbFournisseur.setItems(fournisseursList);

            // Select the first item if available
            if (!fournisseursList.isEmpty()) {
                cmbFournisseur.setValue(fournisseursList.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the error appropriately
        }
    }

    public void setModeAjout() {
        this.isAddMode = true;
        this.stock = new Stock();
    }

    public void setStock(Stock stock) {
        this.isAddMode = false;
        this.stock = stock;

        // Fill the form with stock data
        if (stock != null) {
            txtNomMedicament.setText(stock.getNomMedicament());

            // If in edit mode, disable nomMedicament field as it's the identifier
            if (!isAddMode) {
                txtNomMedicament.setEditable(false);
            }

            // Set the fournisseur in the ComboBox
            if (stock.getFournisseur() != null) {
                // Try to find the fournisseur in the ComboBox items
                for (Fournisseur fournisseur : cmbFournisseur.getItems()) {
                    if (fournisseur.getNom().equals(stock.getFournisseurNom())) {
                        cmbFournisseur.setValue(fournisseur);
                        break;
                    }
                }
            }

            txtPrix.setText(String.valueOf(stock.getPrix()));
            datePicker.setValue(stock.getDateEntree());
            txtQuantite.setText(String.valueOf(stock.getQuantite()));
        }
    }

    public Stock getStock() {
        // Get data from form fields
        String nomMedicament = txtNomMedicament.getText();
        Fournisseur fournisseur = cmbFournisseur.getValue();
        double prix = Double.parseDouble(txtPrix.getText());
        LocalDate dateEntree = datePicker.getValue();
        int quantite = Integer.parseInt(txtQuantite.getText());

        // Update the stock object
        if (isAddMode) {
            stock = new Stock(nomMedicament, fournisseur, prix, dateEntree, quantite);
        } else {
            stock.setNomMedicament(nomMedicament);
            stock.setFournisseur(fournisseur);
            stock.setPrix(prix);
            stock.setDateEntree(dateEntree);
            stock.setQuantite(quantite);
        }

        return stock;
    }
}