package com.example.gestion_pharmacie.dao;

import com.example.gestion_pharmacie.model.Fournisseur;
import com.example.gestion_pharmacie.database.DButil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {

    // Add a new fournisseur to the database
    public void addFournisseur(Fournisseur fournisseur) throws SQLException {
        String query = "INSERT INTO fournisseur (nom, adresse, telephone) VALUES (?, ?, ?)";
        try (Connection connection = DButil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, fournisseur.getNom());
            ps.setString(2, fournisseur.getAdresse());
            ps.setString(3, fournisseur.getTelephone());
            ps.executeUpdate();
        }
    }

    // Delete a fournisseur from the database
    public void deleteFournisseur(int idFournisseur) throws SQLException {
        String query = "DELETE FROM fournisseur WHERE id_fournisseur = ?";
        try (Connection connection = DButil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idFournisseur);
            ps.executeUpdate();
        }
    }

    // Update a fournisseur's details in the database
    public void updateFournisseur(Fournisseur fournisseur) throws SQLException {
        String query = "UPDATE fournisseur SET nom = ?, adresse = ?, telephone = ? WHERE id_fournisseur = ?";
        try (Connection connection = DButil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, fournisseur.getNom());
            ps.setString(2, fournisseur.getAdresse());
            ps.setString(3, fournisseur.getTelephone());
            ps.setInt(4, fournisseur.getIdFournisseur());
            ps.executeUpdate();
        }
    }

    // Get a list of all fournisseurs
    public List<Fournisseur> getAllFournisseurs() throws SQLException {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String query = "SELECT * FROM fournisseur";
        try (Connection connection = DButil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Fournisseur fournisseur = new Fournisseur(
                        rs.getInt("id_fournisseur"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone")
                );
                fournisseurs.add(fournisseur);
            }
        }
        return fournisseurs;
    }

    // Get a fournisseur by id
    public Fournisseur getFournisseurById(int idFournisseur) throws SQLException {
        String query = "SELECT * FROM fournisseur WHERE id_fournisseur = ?";
        try (Connection connection = DButil.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idFournisseur);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Fournisseur(
                            rs.getInt("id_fournisseur"),
                            rs.getString("nom"),
                            rs.getString("adresse"),
                            rs.getString("telephone")
                    );
                }
            }
        }
        return null;
    }


}
