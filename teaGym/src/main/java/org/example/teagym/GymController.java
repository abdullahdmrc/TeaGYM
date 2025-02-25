package org.example.teagym;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GymController {
    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");
    }
}