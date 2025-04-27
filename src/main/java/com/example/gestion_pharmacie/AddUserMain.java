package com.example.gestion_pharmacie;

import com.example.gestion_pharmacie.service.UserService;

public class AddUserMain {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Ajouter un utilisateur avec un login et un mot de passe
        boolean isUserAdded = userService.registerUser("pharmacien1", "admin123");
        if (isUserAdded) {
            System.out.println("Utilisateur ajouté avec succès!");
        } else {
            System.out.println("Échec de l'ajout de l'utilisateur.");
        }


    }
}
