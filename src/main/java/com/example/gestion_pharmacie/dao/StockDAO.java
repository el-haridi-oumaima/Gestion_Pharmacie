package com.example.gestion_pharmacie.dao;

import com.example.gestion_pharmacie.database.DButil;
import com.example.gestion_pharmacie.model.Stock;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    private Connection getConnection() throws SQLException {
        return DButil.getConnection();
    }

    public void ajouterStock(Stock stock) {
        String sql = "INSERT INTO stock (nomMedicament, fournisseur, prix, dateEntree, quantite) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, stock.getNomMedicament());
                stmt.setString(2, stock.getFournisseur());
                stmt.setDouble(3, stock.getPrix());
                stmt.setDate(4, Date.valueOf(stock.getDateEntree()));
                stmt.setInt(5, stock.getQuantite());

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            stock.setIdStock(generatedKeys.getInt(1));
                        }
                    }
                }

                conn.commit();
                System.out.println("Stock ajouté avec succès");

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du stock : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Stock> recupererTousLesStocks() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM stock";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idStock = rs.getInt("idStock");
                String nomMedicament = rs.getString("nomMedicament");
                String fournisseur = rs.getString("fournisseur");
                double prix = rs.getDouble("prix");
                LocalDate dateEntree = rs.getDate("dateEntree").toLocalDate();
                int quantite = rs.getInt("quantite");

                Stock stock = new Stock(idStock, nomMedicament, fournisseur, prix, dateEntree, quantite);
                stocks.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stocks;
    }

    public void supprimerStock(String nomMedicament) {
        String sql = "DELETE FROM stock WHERE nomMedicament = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomMedicament);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierStock(Stock stock) {
        String sql = "UPDATE stock SET fournisseur = ?, prix = ?, dateEntree = ?, quantite = ? WHERE idStock = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, stock.getFournisseur());
            stmt.setDouble(2, stock.getPrix());
            stmt.setDate(3, Date.valueOf(stock.getDateEntree()));
            stmt.setInt(4, stock.getQuantite());
            stmt.setInt(5, stock.getIdStock());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Stock recupererStockParNom(String nomMedicament) {
        String sql = "SELECT * FROM stock WHERE nomMedicament = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomMedicament);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idStock = rs.getInt("idStock");
                String fournisseur = rs.getString("fournisseur");
                double prix = rs.getDouble("prix");
                LocalDate dateEntree = rs.getDate("dateEntree").toLocalDate();
                int quantite = rs.getInt("quantite");

                return new Stock(idStock, nomMedicament, fournisseur, prix, dateEntree, quantite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Stock> recupererStocksFaibles() {
        List<Stock> stocksFaibles = new ArrayList<>();
        String sql = "SELECT * FROM stock WHERE quantite < 3";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idStock = rs.getInt("idStock");
                String nomMedicament = rs.getString("nomMedicament");
                String fournisseur = rs.getString("fournisseur");
                double prix = rs.getDouble("prix");
                LocalDate dateEntree = rs.getDate("dateEntree").toLocalDate();
                int quantite = rs.getInt("quantite");

                Stock stock = new Stock(idStock, nomMedicament, fournisseur, prix, dateEntree, quantite);
                stocksFaibles.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stocksFaibles;
    }

}
