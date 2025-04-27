module com.example.gestion_pharmacie {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    requires jbcrypt;


    opens com.example.gestion_pharmacie to javafx.fxml;
    exports com.example.gestion_pharmacie;
    exports com.example.gestion_pharmacie.controller;
    opens com.example.gestion_pharmacie.controller to javafx.fxml;
    exports com.example.gestion_pharmacie.model;
    opens com.example.gestion_pharmacie.model to javafx.fxml;
    exports com.example.gestion_pharmacie.dao;
    opens com.example.gestion_pharmacie.dao to javafx.fxml;
    exports com.example.gestion_pharmacie.service;
    opens com.example.gestion_pharmacie.service to javafx.fxml;
    exports com.example.gestion_pharmacie.database;
    opens com.example.gestion_pharmacie.database to javafx.fxml;
}