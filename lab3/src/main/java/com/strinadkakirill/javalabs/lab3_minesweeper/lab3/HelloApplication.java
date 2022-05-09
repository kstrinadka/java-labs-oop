package com.strinadkakirill.javalabs.lab3_minesweeper.lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-menu.fxml"));

        //MenuController menuController = new MenuController();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("myControllers/guiControllers/main-menu.fxml"));
//        try {
//            loader.load();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        stage.setMinHeight(400);
        stage.setMinWidth(400);

        Scene scene = new Scene(loader.load());
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}