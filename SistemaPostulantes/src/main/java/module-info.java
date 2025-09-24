module com.example.sistemapostulantes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sistemapostulantes to javafx.fxml;
    exports com.example.sistemapostulantes;
}