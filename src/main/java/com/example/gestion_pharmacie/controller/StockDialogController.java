package com.example.gestion_pharmacie.controller;

import com.example.gestion_pharmacie.model.Stock;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class StockDialogController {

    @FXML
    private TextField txtMedicament;

    @FXML
    private TextField txtFournisseur;

    @FXML
    private TextField txtPrix;

    @FXML
    private DatePicker dpDateEntree;

    @FXML
    private TextField txtQuantite;

    private boolean modeAjout = true;

    private int idStock = -1; // Identifiant pour la modification

    @FXML
    public void initialize() {
        configurerValidationChamps();
        dpDateEntree.setValue(LocalDate.now());
    }

    private void configurerValidationChamps() {
        txtPrix.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                txtPrix.setText(oldValue);
            }
        });

        txtQuantite.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtQuantite.setText(oldValue);
            }
        });
    }

    public void setModeAjout() {
        this.modeAjout = true;
        txtMedicament.setEditable(true);
        effacerChamps();
    }

    public void setStock(Stock stock) {
        this.modeAjout = false;
        this.idStock = stock.getIdStock(); // ✅ Sauvegarde de l'ID pour modification

        txtMedicament.setText(stock.getNomMedicament());
        txtMedicament.setEditable(false); // Nom non modifiable

        txtFournisseur.setText(stock.getFournisseur());
        txtPrix.setText(String.valueOf(stock.getPrix()));
        dpDateEntree.setValue(stock.getDateEntree());
        txtQuantite.setText(String.valueOf(stock.getQuantite()));
    }

    public Stock getStock() {
        String nomMedicament = txtMedicament.getText().trim();
        String fournisseur = txtFournisseur.getText().trim();
        double prix = Double.parseDouble(txtPrix.getText().trim());
        LocalDate dateEntree = dpDateEntree.getValue();
        int quantite = Integer.parseInt(txtQuantite.getText().trim());

        Stock stock = new Stock(nomMedicament, fournisseur, prix, dateEntree, quantite);

        if (!modeAjout) {
            stock.setIdStock(this.idStock); // ✅ Réinjecte l'ID pour la modification
        }

        return stock;
    }

    private void effacerChamps() {
        txtMedicament.clear();
        txtFournisseur.clear();
        txtPrix.clear();
        dpDateEntree.setValue(LocalDate.now());
        txtQuantite.clear();
    }

    public boolean validerChamps() {
        if (txtMedicament.getText().trim().isEmpty() ||
                txtFournisseur.getText().trim().isEmpty() ||
                txtPrix.getText().trim().isEmpty() ||
                dpDateEntree.getValue() == null ||
                txtQuantite.getText().trim().isEmpty()) {
            return false;
        }

        try {
            Double.parseDouble(txtPrix.getText().trim());
            Integer.parseInt(txtQuantite.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
