package com.example.gestion_pharmacie;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO(); // Initialisation de UserDAO
    }

    // Méthode pour ajouter un utilisateur
    public boolean registerUser(String login, String password) {
        if (isValidLogin(login) && isValidPassword(password)) {
            return userDAO.addUser(login, password); // Utilisation de UserDAO pour ajouter l'utilisateur
        }
        return false; // Si le login ou le mot de passe n'est pas valide
    }

    // Méthode pour vérifier les identifiants de l'utilisateur
    public boolean authenticateUser(String login, String password) {
        String storedPasswordHash = userDAO.getPasswordHash(login);
        if (storedPasswordHash != null) {
            return BCrypt.checkpw(password, storedPasswordHash); // Vérification avec bcrypt
        }
        return false; // Si l'utilisateur n'existe pas
    }


    // Validation du login (exemple simple, à adapter selon les besoins)
    private boolean isValidLogin(String login) {
        return login != null && !login.trim().isEmpty(); // Exemples de validations, peut être enrichi
    }

    // Validation du mot de passe (exemple simple)
    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6; // Longueur minimale
    }
}
