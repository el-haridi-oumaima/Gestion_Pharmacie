package com.example.gestion_pharmacie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    // Méthode pour ajouter un utilisateur dans la base de données
    public boolean addUser(String login, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt()); // Hachage du mot de passe
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, hashedPassword);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Retourne vrai si l'insertion a réussi
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour vérifier les identifiants de l'utilisateur
    public String getPasswordHash(String login) {
        String sql = "SELECT password FROM users WHERE login = ?";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("password"); // Retourne le mot de passe haché
            } else {
                return null; // L'utilisateur n'existe pas
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
