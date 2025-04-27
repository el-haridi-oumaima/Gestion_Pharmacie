module com.example.gestion_pharmacie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestion_pharmacie to javafx.fxml;
    exports com.example.gestion_pharmacie;
}