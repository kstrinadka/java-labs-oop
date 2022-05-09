package com.strinadkakirill.javalabs.lab3_minesweeper.lab3.myControllers.guiControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChoosingGameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainMenu;


    @FXML
    void initialize() {
        assert mainMenu != null : "fx:id=\"mainMenu\" was not injected: check your FXML file 'choosing-game-mode.fxml'.";

    }

    public void buttonBegginerAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("game.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void buttonIntermediateAction(ActionEvent actionEvent) {
    }

    public void buttonProfessionalAction(ActionEvent actionEvent) {
    }

    public void buttonSpecificAction(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("bad-choice.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            stage.setTitle("неправильные данные");
            stage.setMinWidth(400);
            stage.setMinHeight(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buttonMainMenuAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main-menu.fxml"));

            loader.load();

            Parent root = loader.getRoot();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
