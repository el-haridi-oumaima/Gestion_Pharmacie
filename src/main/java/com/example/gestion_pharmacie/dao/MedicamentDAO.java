package com.example.gestion_pharmacie.dao;

import com.example.gestion_pharmacie.model.Medicament;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentDAO {

    private Connection connection;

    public MedicamentDAO(Connection connection) {
        this.connection = connection;
    }

    // Ajouter un médicament
    public void ajouterMedicament(Medicament medicament) {
        String sql = "INSERT INTO medicament (nom, description, prix_vente, prix_achat, date_expiration, nom_fournisseur) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, medicament.getNom());
            ps.setString(2, medicament.getDescription());
            ps.setDouble(3, medicament.getPrixVente());
            ps.setDouble(4, medicament.getPrixAchat());
            ps.setDate(5, new java.sql.Date(medicament.getDateExpiration().getTime()));
            ps.setString(6, medicament.getNomFournisseur());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un médicament
    public void supprimerMedicament(int id) {
        String sql = "DELETE FROM medicament WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mettre à jour un médicament
    public void mettreAJourMedicament(Medicament medicament) {
        String sql = "UPDATE medicament SET nom = ?, description = ?, prix_vente = ?, prix_achat = ?, date_expiration = ?, nom_fournisseur = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, medicament.getNom());
            ps.setString(2, medicament.getDescription());
            ps.setDouble(3, medicament.getPrixVente());
            ps.setDouble(4, medicament.getPrixAchat());
            ps.setDate(5, new java.sql.Date(medicament.getDateExpiration().getTime()));
            ps.setString(6, medicament.getNomFournisseur());
            ps.setInt(7, medicament.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Rechercher des médicaments par nom et fournisseur
    public List<Medicament> rechercherMedicaments(String nom, String fournisseur) {
        List<Medicament> medicaments = new ArrayList<>();
        String sql = "SELECT * FROM medicament WHERE nom LIKE ? AND nom_fournisseur LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nom + "%"); // Recherche avec wildcard (partie du nom)
            ps.setString(2, "%" + fournisseur + "%"); // Recherche avec wildcard (partie du fournisseur)
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medicament medicament = new Medicament(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDouble("prix_vente"),
                        rs.getDouble("prix_achat"),
                        rs.getDate("date_expiration"),
                        rs.getString("nom_fournisseur")
                );
                medicaments.add(medicament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicaments;
    }
}