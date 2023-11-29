package com.example.workbench;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



import java.io.IOException;

public class BenchSQL extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Image icon = new Image("D:\\BenchSQL\\WorkBench\\media\\icon.png");

        Parent sb = FXMLLoader.load(getClass().getResource("login.fxml"));

        stage.setTitle("MySQL WorkBench");
        stage.getIcons().add(icon);

        //stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setScene(new Scene(sb));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}