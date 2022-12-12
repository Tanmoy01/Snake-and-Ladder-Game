package com.example.snakeandladder;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Group root;
    @Override
    public void start(Stage primaryStage) throws Exception {

        root= new Group();
        GamePage page= new GamePage();
        root.getChildren().add(page.root);
        primaryStage.setTitle("Snake And Ladder");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}