package com.github.jingerjesus.thehellchess.control;

import com.github.jingerjesus.thehellchess.game.Board;
import com.github.jingerjesus.thehellchess.peripherals.FileInput;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class UI extends Application {
    private Stage stageM = new Stage();
    private Scene mainScene;
    public static Group mainGroup = new Group();
    private Board board = new Board();
    @Override
    public void start(Stage stage) throws IOException {


        //do ui design or dig up the old one we drew years ago
        //board is uhhh 16x16 (x7 possibly if we get this standard board working)
        mainScene = new Scene(mainGroup, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        stageM.setTitle("Hell Chess 0.2.0");
        stageM.setScene(mainScene);

        GameController.initPieces(true);
        loop.setCycleCount(Animation.INDEFINITE);
        loop.play();

        mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Mouse X: " + mouseEvent.getSceneX() + ", Mouse Y: " + mouseEvent.getSceneY());

            }
        });


        stageM.show();
    }




    KeyFrame animate = new KeyFrame(

            //Common FPS Values:
            //120 FPS: 0.0083 sec
            //60 FPS: 0.0167 sec
            //35 FPS: 0.029 sec
            //1 FPS: 1.0 sec
            Duration.seconds(0.029),
            actionEvent -> {
                board.updateBoard();
                stageM.show();
            }
    );

    Timeline loop = new Timeline(animate);

    public static void main(String[] args) {
        launch();
    }
}