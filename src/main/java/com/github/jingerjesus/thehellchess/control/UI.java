package com.github.jingerjesus.thehellchess.control;

import com.github.jingerjesus.thehellchess.game.Board;
import com.github.jingerjesus.thehellchess.peripherals.FileInput;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UI extends Application {
    private Scene mainScene;
    public static Group mainGroup = new Group();
    @Override
    public void start(Stage stage) throws IOException {


        //do ui design or dig up the old one we drew years ago
        //board is uhhh 16x16 (x7 possibly if we get this standard board working)
        mainScene = new Scene(mainGroup, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        stage.setTitle("Hell Chess 0.2.0");
        stage.setScene(mainScene);
        Board board = new Board();
        GameController.initPieces(true);
        board.updateBoard();

        board.pieceAt(0,15).addBishopMoves(board.tiles);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}