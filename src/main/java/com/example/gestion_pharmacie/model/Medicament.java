package com.example.gestion_pharmacie.model;

import java.util.Date;

public class Medicament {
    private int id;                // Identifiant unique du médicament
    private String nom;            // Nom du médicament
    private String description;    // Description du médicament
    private double prixVente;      // Prix de vente du médicament
    private double prixAchat;      // Prix d'achat du médicament
    private Date dateExpiration;   // Date d'expiration du médicament
    private String nomFournisseur; // Nom du fournisseur du médicament

    // Constructeur
    public Medicament(int id, String nom, String description, double prixVente, double prixAchat, Date dateExpiration, String nomFournisseur) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prixVente = prixVente;
        this.prixAchat = prixAchat;
        this.dateExpiration = dateExpiration;
        this.nomFournisseur = nomFournisseur;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }
}
