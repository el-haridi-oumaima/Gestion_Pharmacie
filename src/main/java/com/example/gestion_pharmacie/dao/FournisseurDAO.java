package com.example.gestion_pharmacie.dao;

import com.example.gestion_pharmacie.database.DButil;
import com.example.gestion_pharmacie.model.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO {

    private Connection getConnection() throws SQLException {
        return DButil.getConnection();
    }

    // Add a new fournisseur
    public void addFournisseur(Fournisseur fournisseur) throws SQLException {
        String sql = "INSERT INTO fournisseurs (nom, adresse, telephone) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, fournisseur.getNom());
            stmt.setString(2, fournisseur.getAdresse());
            stmt.setString(3, fournisseur.getTelephone());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        fournisseur.setIdFournisseur(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    // Delete a fournisseur
    public void deleteFournisseur(int idFournisseur) throws SQLException {
        String sql = "DELETE FROM fournisseurs WHERE idFournisseur = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFournisseur);
            stmt.executeUpdate();
        }
    }

    // Update an existing fournisseur
    public void updateFournisseur(Fournisseur fournisseur) throws SQLException {
        String sql = "UPDATE fournisseurs SET nom = ?, adresse = ?, telephone = ? WHERE idFournisseur = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fournisseur.getNom());
            stmt.setString(2, fournisseur.getAdresse());
            stmt.setString(3, fournisseur.getTelephone());
            stmt.setInt(4, fournisseur.getIdFournisseur());

            stmt.executeUpdate();
        }
    }

    // Get a list of all fournisseurs
    public List<Fournisseur> getAllFournisseurs() throws SQLException {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String sql = "SELECT * FROM fournisseurs";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idFournisseur = rs.getInt("idFournisseur");
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");

                Fournisseur fournisseur = new Fournisseur(idFournisseur, nom, adresse, telephone);
                fournisseurs.add(fournisseur);
            }
        }

        return fournisseurs;
    }

    // Get a fournisseur by ID
    public Fournisseur getFournisseurById(int idFournisseur) throws SQLException {
        String sql = "SELECT * FROM fournisseurs WHERE idFournisseur = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFournisseur);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");

                return new Fournisseur(idFournisseur, nom, adresse, telephone);
            }
        }

        return null;
    }

    // Get a fournisseur by name
    public Fournisseur getFournisseurByNom(String nom) throws SQLException {
        String sql = "SELECT * FROM fournisseurs WHERE nom = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idFournisseur = rs.getInt("idFournisseur");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");

                return new Fournisseur(idFournisseur, nom, adresse, telephone);
            }
        }

        return null;
    }
}