package com.strinadkakirill.javalabs.lab3_minesweeper.lab3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    private Label diswelcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onGoodByeButtonClick() {
        welcomeText.setText("нажал на кнопку, значит пидор");
    }

}