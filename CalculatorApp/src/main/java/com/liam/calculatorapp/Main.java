package com.liam.calculatorapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("calcview.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 279, 368);

        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}