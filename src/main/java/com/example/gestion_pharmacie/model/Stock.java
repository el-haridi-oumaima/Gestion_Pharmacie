package com.example.gestion_pharmacie.model;

import java.time.LocalDate;

public class Stock {
    private int idStock;  // Primary key
    private String nomMedicament;
    private String fournisseur;
    private double prix;
    private LocalDate dateEntree;
    private int quantite;

    // Constructeur sans idStock (car il est auto-incrémenté)
    public Stock(String nomMedicament, String fournisseur, double prix, LocalDate dateEntree, int quantite) {
        this.nomMedicament = nomMedicament;
        this.fournisseur = fournisseur;
        this.prix = prix;
        this.dateEntree = dateEntree;
        this.quantite = quantite;
    }

    // Constructeur avec idStock pour les cas où l'ID est déjà généré par la BDD
    public Stock(int idStock, String nomMedicament, String fournisseur, double prix, LocalDate dateEntree, int quantite) {
        this.idStock = idStock;
        this.nomMedicament = nomMedicament;
        this.fournisseur = fournisseur;
        this.prix = prix;
        this.dateEntree = dateEntree;
        this.quantite = quantite;
    }

    // Getters
    public int getIdStock() { return idStock; }
    public String getNomMedicament() { return nomMedicament; }
    public String getFournisseur() { return fournisseur; }
    public double getPrix() { return prix; }
    public LocalDate getDateEntree() { return dateEntree; }
    public int getQuantite() { return quantite; }

    // Setters
    public void setIdStock(int idStock) { this.idStock = idStock; }
    public void setNomMedicament(String nomMedicament) { this.nomMedicament = nomMedicament; }
    public void setFournisseur(String fournisseur) { this.fournisseur = fournisseur; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setDateEntree(LocalDate dateEntree) { this.dateEntree = dateEntree; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    @Override
    public String toString() {
        return "Stock{" +
                "idStock=" + idStock +
                ", nomMedicament='" + nomMedicament + '\'' +
                ", fournisseur='" + fournisseur + '\'' +
                ", prix=" + prix +
                ", dateEntree=" + dateEntree +
                ", quantite=" + quantite +
                '}';
    }
}
