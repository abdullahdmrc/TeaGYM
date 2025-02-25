module org.example.teagym {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.teagym to javafx.fxml;
    exports org.example.teagym;
}