package com.example.gestion_pharmacie.service;

import com.example.gestion_pharmacie.dao.FournisseurDAO;
import com.example.gestion_pharmacie.model.Fournisseur;
import java.sql.SQLException;
import java.util.List;

public class FournisseurService {

    private FournisseurDAO fournisseurDAO;

    public FournisseurService() {
        this.fournisseurDAO = new FournisseurDAO();
    }

    // Add a new fournisseur
    public void addFournisseur(Fournisseur fournisseur) throws SQLException {
        fournisseurDAO.addFournisseur(fournisseur);
    }

    // Delete a fournisseur
    public void deleteFournisseur(int idFournisseur) throws SQLException {
        fournisseurDAO.deleteFournisseur(idFournisseur);
    }

    // Update an existing fournisseur
    public void updateFournisseur(Fournisseur fournisseur) throws SQLException {
        fournisseurDAO.updateFournisseur(fournisseur);
    }

    // Get a list of all fournisseurs
    public List<Fournisseur> getAllFournisseurs() throws SQLException {
        return fournisseurDAO.getAllFournisseurs();
    }

    // Get a fournisseur by ID
    public Fournisseur getFournisseurById(int idFournisseur) throws SQLException {
        return fournisseurDAO.getFournisseurById(idFournisseur);
    }
}