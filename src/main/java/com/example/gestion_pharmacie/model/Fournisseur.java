package com.example.gestion_pharmacie.model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
public class Fournisseur {

    private IntegerProperty idFournisseur;
    private StringProperty nom;
    private StringProperty adresse;
    private StringProperty telephone;

    // Constructeur
    public Fournisseur(int idFournisseur, String nom, String adresse, String telephone) {
        this.idFournisseur = new SimpleIntegerProperty(idFournisseur);
        this.nom = new SimpleStringProperty(nom);
        this.adresse = new SimpleStringProperty(adresse);
        this.telephone = new SimpleStringProperty(telephone);
    }

    // Getters et Setters
    public IntegerProperty idFournisseurProperty() {
        return idFournisseur;
    }

    public int getIdFournisseur() {
        return idFournisseur.get();
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur.set(idFournisseur);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty adresseProperty() {
        return adresse;
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }
}
