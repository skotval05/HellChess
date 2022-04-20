package com.github.jingerjesus.thehellchess.control;

import com.github.jingerjesus.thehellchess.game.Board;
import com.github.jingerjesus.thehellchess.game.Piece;
import com.github.jingerjesus.thehellchess.game.Player;
import com.github.jingerjesus.thehellchess.game.Tile;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class UI extends Application {
    private Stage stageM = new Stage();
    private Scene mainScene;
    public static Group mainGroup = new Group();
    private Board board = new Board();
    private Tile selectedTile = null;
    private  Tile movingTile = null;

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
            public void handle(MouseEvent mouseDragEvent) {
                System.out.println("Enter Drag");
                int mouseTileX = (int) mouseDragEvent.getSceneX() / Constants.TILE_SIZE;
                int mouseTileY = (int) mouseDragEvent.getSceneY() / Constants.TILE_SIZE;
                System.out.println("Drag start at " + mouseTileX + " " + mouseTileY);

                selectedTile = board.tiles[mouseTileX][mouseTileY];

                if (selectedTile != null && isValidSelect(selectedTile, GameController.turn) && selectedTile.occupiedBy != Constants.NO_PIECE) {

                    board.showMoves(selectedTile.occupiedBy.addMovesOfType(selectedTile.occupiedBy.type, board.tiles), selectedTile.occupiedBy);

                    board.highlights[selectedTile.x / Constants.TILE_SIZE][selectedTile.y / Constants.TILE_SIZE].setHighlight(Constants.HighlightType.SELECTED);

                }
            }
        });

        mainScene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseDragEvent) {
                System.out.println("exit Drag");
                int mouseTileX = (int) mouseDragEvent.getSceneX() / Constants.TILE_SIZE;
                int mouseTileY = (int) mouseDragEvent.getSceneY() / Constants.TILE_SIZE;
                System.out.println("Drag exits at " + mouseTileX + " " + mouseTileY);

                movingTile = board.tiles[mouseTileX][mouseTileY];

                if (selectedTile != null && movingTile != selectedTile && selectedTile.occupiedBy != Constants.NO_PIECE) {
                    board.highlights[selectedTile.x / Constants.TILE_SIZE][selectedTile.y / Constants.TILE_SIZE].setHighlight(Constants.HighlightType.NONE);

                    if (isValidMove(movingTile)) {

                        if (movingTile.occupiedBy != Constants.NO_PIECE) {
                            Piece remove = board.pieceAt(selectedTile.x/Constants.TILE_SIZE, selectedTile.y/Constants.TILE_SIZE);
                            if (Constants.PLAYER_ONE.equals(remove.owner)) {
                                GameController.playerOnePieces.remove(remove);
                            } else if (Constants.PLAYER_TWO.equals(remove.owner)) {
                                GameController.playerTwoPieces.remove(remove);
                            } else if (Constants.PLAYER_THREE.equals(remove.owner)) {
                                GameController.playerThreePieces.remove(remove);
                            }
                            remove.x = -1;
                            remove.y = -1;
                        }

                        selectedTile.occupiedBy.x = movingTile.x/Constants.TILE_SIZE;
                        selectedTile.occupiedBy.y = movingTile.y/Constants.TILE_SIZE;
                        selectedTile.occupiedBy.isFirstTurn = false;

                        GameController.cycleTurn();
                        board.updateBoard();

                    }
                }

                board.removeHighlights();

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

    private boolean isValidSelect(Tile tile, Player turn) {
        boolean temp = true;

        if (tile.occupiedBy == Constants.NO_PIECE || tile.occupiedBy.owner != turn) {
            temp = false;
        }

        return temp;
    }

    private boolean isValidMove(Tile tile) {
        boolean temp = true;

        if (board.highlights[tile.x/Constants.TILE_SIZE][tile.y/Constants.TILE_SIZE].getHighlight() == Constants.HighlightType.NONE
        || board.highlights[tile.x/Constants.TILE_SIZE][tile.y/Constants.TILE_SIZE].getHighlight() == Constants.HighlightType.SELECTED) {
            temp = false;
        }

        System.out.println("Is that drag a valid move: " + temp);

        return temp;
    }

    KeyFrame animate = new KeyFrame(

            //Common FPS Values:
            //120 FPS: 0.0083 sec
            //60 FPS: 0.0167 sec
            //35 FPS: 0.029 sec
            //1 FPS: 1.0 sec
            Duration.seconds(0.029),
            actionEvent -> {
            }
    );

    Timeline loop = new Timeline(animate);

    public static void main(String[] args) {
        launch();
    }
}