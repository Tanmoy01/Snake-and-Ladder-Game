package com.example.snakeandladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class PlayAreaController {

    @FXML
    Text number;
    @FXML
            Text changeturn;
    @FXML
    ImageView player1, player2,first, second;
    int turn=1;

    HashMap<Pair<Double, Double>, Pair<Double,Double>> snakeLaderCoordinate;
    @FXML
    public void roll(MouseEvent event) throws IOException {
        getSnakeLadderCoordinates();
        //System.out.println("Dice is Rolling");
        Random random = new Random();
        int rolling = random.nextInt(6) + 1;
        number.setText("" + rolling);
        //System.out.println(rolling);

        if(turn ==1) {
            Pair<Double, Double> moveCoordinates= movement(player1.getTranslateX(), player1.getTranslateY(), rolling);
            player1.setTranslateX(moveCoordinates.getKey());
            player1.setTranslateY(moveCoordinates.getValue());
            if(snakeLaderCoordinate.containsKey(new Pair<>(moveCoordinates.getKey(), moveCoordinates.getValue()))){
                player1.setTranslateX(snakeLaderCoordinate.get
                        (new Pair<>(moveCoordinates.getKey(), moveCoordinates.getValue())).getKey());
                player1.setTranslateY(snakeLaderCoordinate.get
                        (new Pair<>(moveCoordinates.getKey(), moveCoordinates.getValue())).getValue());
            }
            checkWin(player1.getTranslateX(), player1.getTranslateY());
        }
        else{
            Pair<Double, Double> moveCoordinates= movement(player2.getTranslateX(), player2.getTranslateY(), rolling);
            player2.setTranslateX(moveCoordinates.getKey());
            player2.setTranslateY(moveCoordinates.getValue());
            if(snakeLaderCoordinate.containsKey(new Pair<>(moveCoordinates.getKey(), moveCoordinates.getValue()))){
                player2.setTranslateX(snakeLaderCoordinate.get
                        (new Pair<>(moveCoordinates.getKey(), moveCoordinates.getValue())).getKey());
                player2.setTranslateY(snakeLaderCoordinate.get
                        (new Pair<>(moveCoordinates.getKey(), moveCoordinates.getValue())).getValue());
            }
            checkWin(player2.getTranslateX(), player2.getTranslateY());
        }
        if (rolling != 6) {
            if (turn == 1) {
                turn = 2;
                changeturn.setText("Player 2's turn");
                first.setOpacity(0);
                second.setOpacity(1);
            } else {
                turn = 1;
                changeturn.setText("Player 1's turn");
                second.setOpacity(0);
                first.setOpacity(1);
            }
        }
    }
        Pair<Double, Double> movement(double X, double Y, int rolling){
            double moveX= X;
            double moveY= Y;

            if(moveY % 100 == 0) {
                moveX += rolling*50;
                if (moveX > 500) {
                    moveX = 500 * 2 - moveX + 50;
                    moveY -= 50;
                }
            }
            else{
                moveX -= rolling*50;
                if(moveX<50){
                    if(moveY == -450)
                        return(new Pair<>(X, Y));
                    moveX= -1*(moveX - 50);
                    moveY -= 50;
                }
            }

            X += rolling * 50;
            return new Pair<>(moveX, moveY);
        }
        void checkWin(Double X, Double Y) throws IOException {
        if(X== 50 && Y == -450){
            Alert winAleart = new Alert(Alert.AlertType.INFORMATION);
            winAleart.setHeaderText("Result");
            if(turn ==1) {
                winAleart.setContentText("Player 1 has won the game");
            }
            else
            {
                winAleart.setContentText("Player 2 has won the game");
            }
            GamePage page= new GamePage();
            Main.root.getChildren().setAll(page.root);
            winAleart.showAndWait();
        }

        }
    void getSnakeLadderCoordinates(){
        snakeLaderCoordinate=new HashMap<>();
        snakeLaderCoordinate.put(new Pair <>(50.0,0.0), new Pair<>(150.0, -150.0));
        snakeLaderCoordinate.put(new Pair <>(200.0,0.0), new Pair<>(350.0, -50.0));
        snakeLaderCoordinate.put(new Pair <>(200.0,-50.0), new Pair<>(350.0, 0.0));
        snakeLaderCoordinate.put(new Pair <>(450.0,0.0), new Pair<>(500.0, -150.0));
        snakeLaderCoordinate.put(new Pair <>(50.0,-100.0), new Pair<>(100.0, -200.0));
        snakeLaderCoordinate.put(new Pair <>(400.0,-100.0), new Pair<>(200.0, -400.0));
        snakeLaderCoordinate.put(new Pair <>(100.0,-300.0), new Pair<>(100.0, -50.0));
        snakeLaderCoordinate.put(new Pair <>(50.0,0.0), new Pair<>(150.0, -150.0));
        snakeLaderCoordinate.put(new Pair <>(500.0,-250.0), new Pair<>(350.0, -300.0));
        snakeLaderCoordinate.put(new Pair <>(500.0,-350.0), new Pair<>(500.0, -450.0));
        snakeLaderCoordinate.put(new Pair <>(50.0,-350.0), new Pair<>(50.0, -450.0));
        snakeLaderCoordinate.put(new Pair <>(200.0,-300.0), new Pair<>(50.0, -250.0));
        snakeLaderCoordinate.put(new Pair <>(350.0,-250.0), new Pair<>(350.0, -150.0));
        snakeLaderCoordinate.put(new Pair <>(150.0,-450.0), new Pair<>(100.0, -350.0));
        snakeLaderCoordinate.put(new Pair <>(300.0,-450.0), new Pair<>(300.0, -350.0));
        snakeLaderCoordinate.put(new Pair <>(400.0,-450.0), new Pair<>(400.0, -350.0));
        snakeLaderCoordinate.put(new Pair <>(350.0,-400.0), new Pair<>(200.0, -100.0));
    }
}
