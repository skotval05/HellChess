package com.github.jingerjesus.thehellchess.control;

import com.github.jingerjesus.thehellchess.game.Board;
import com.github.jingerjesus.thehellchess.game.Tile;
import com.github.jingerjesus.thehellchess.peripherals.FileInput;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.IOException;

public class UI extends Application {
    private Stage stageM = new Stage();
    private Scene mainScene;
    public static Group mainGroup = new Group();
    private Board board = new Board();
    private Tile tileClicked = null;
    private Tile selectedTile = null;
    private boolean movingClick = false;
    private boolean selectingClick = true;

    @Override
    public void start(Stage stage) {


        //do ui design or dig up the old one we drew years ago
        //board is uhhh 16x16 (x7 possibly if we get this standard board working)
        mainScene = new Scene(mainGroup, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        stageM.setTitle("Hell Chess 0.2.0");
        stageM.setScene(mainScene);

        GameController.initPieces(true);
        board.updateBoard();
        //board.pieceAt(0, 14).addPawnMoves(board.tiles);
        stageM.show();
        loop.setCycleCount(Animation.INDEFINITE);
        loop.play();


        //piggyback off of this event since we hardly need threads
        //love this dont we
        mainScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                System.out.println("Mouse X: " + mouseEvent.getSceneX() + ", Mouse Y: " + mouseEvent.getSceneY());
                //System.out.println(mouseEvent.getSceneX() / Constants.TILE_SIZE + " " + mouseEvent.getSceneY() / Constants.TILE_SIZE);
                int x = (int)mouseEvent.getSceneX() / Constants.TILE_SIZE;
                int y = (int)mouseEvent.getSceneY() / Constants.TILE_SIZE;

                tileClicked = board.tiles[x][y];
                /*
                if (tileClicked != null) {
                    if (board.pieceAt(tileClicked.x/Constants.TILE_SIZE, tileClicked.y/Constants.TILE_SIZE) != Constants.NO_PIECE
                            && selectingClick &&
                            board.pieceAt(tileClicked.x/Constants.TILE_SIZE, tileClicked.y/Constants.TILE_SIZE).owner == GameController.turn) {
                        if (selectedTile != null) {
                            board.highlights[selectedTile.x/32][selectedTile.y/32].setHighlight(Constants.HighlightType.NONE);
                        }
                        board.highlights[tileClicked.x/32][tileClicked.y/32].setHighlight(Constants.HighlightType.SELECTED);
                        selectedTile = tileClicked;

                        //selectingClick = false;
                        //movingClick = true;
                    } else {
                        if (movingClick) {

                        } else {
                            System.out.println("No piece on this tile.");
                        }

                    }
                } else System.out.println("No tile clicked.");

                movingClick = false;
                selectingClick = true;
                GameController.cycleTurn();
                board.updateBoard();
                stageM.show();
                */
            }
        });
        stageM.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                loop.stop();
                Platform.exit();
                System.exit(0);
            }
        });

    }



    KeyFrame animate = new KeyFrame(

            //Common FPS Values:
            //120 FPS: 0.0083 sec
            //60 FPS: 0.0167 sec
            //35 FPS: 0.029 sec
            //1 FPS: 1.0 sec
            Duration.seconds(0.029),
            actionEvent -> {
                // /*
                if (tileClicked != null) {
                    if (board.pieceAt(tileClicked.x/Constants.TILE_SIZE, tileClicked.y/Constants.TILE_SIZE) != Constants.NO_PIECE
                            && selectingClick &&
                            board.pieceAt(tileClicked.x/Constants.TILE_SIZE, tileClicked.y/Constants.TILE_SIZE).owner == GameController.turn) {
                        if (selectedTile != null) {
                            board.highlights[selectedTile.x/32][selectedTile.y/32].setHighlight(Constants.HighlightType.NONE);
                        }
                        board.highlights[tileClicked.x/32][tileClicked.y/32].setHighlight(Constants.HighlightType.SELECTED);
                        selectedTile = tileClicked;

                        //selectingClick = false;
                        //movingClick = true;
                    } else {
                        if (movingClick) {

                        } else {
                            System.out.println("No piece on this tile.");
                        }

                    }
                } else System.out.println("No tile clicked.");

                movingClick = false;
                selectingClick = true;
                //GameController.cycleTurn();
                board.updateBoard();
                stageM.show();
                // */
            }
    );

    Timeline loop = new Timeline(animate);

    public static void main(String[] args) {
        launch();
    }
}