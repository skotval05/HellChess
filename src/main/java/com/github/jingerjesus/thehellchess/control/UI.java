package com.github.jingerjesus.thehellchess.control;

import com.github.jingerjesus.thehellchess.game.Board;
import com.github.jingerjesus.thehellchess.game.Piece;
import com.github.jingerjesus.thehellchess.game.Player;
import com.github.jingerjesus.thehellchess.game.Tile;
import com.github.jingerjesus.thehellchess.peripherals.FileInput;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.EventListener;

public class UI extends Application {
    private Stage stageM = new Stage();
    public static Scene mainScene;
    public static Group mainGroup = new Group();
    private Board board = new Board();
    private Tile selectedTile = null;
    private  Tile movingTile = null;
    private ImageView borderView;
    boolean onSplash = true;
    //private ArrayList<Board> boards = new ArrayList<Board>(7);


    @Override
    public void start(Stage stage) {

        stageM.setTitle("Hell Chess v0.1.1-alpha");
        stageM.getIcons().add(FileInput.getImage("RedPawn"));

        doSplashScreen();

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

    private void doSplashScreen() {

        ImageView view = new ImageView(FileInput.getImage("Splash"));
        view.setX(0); view.setY(0);
        view.setFitWidth(Constants.SCREEN_WIDTH); view.setFitHeight(Constants.SCREEN_HEIGHT);

        Group splashGroup = new Group(view);
        Scene splashScreen = new Scene(splashGroup, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        stageM.setScene(splashScreen);

        stageM.show();

        stageM.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                stageM.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        //do nothing
                    }
                });
                if (onSplash) {
                    doGameScreen();
                    onSplash = false;
                }

                return;

            }
        });

    }

    private void doGameScreen() {


        Rectangle turnIndicator = GameController.getTurnInd();
        turnIndicator.setX(2);
        turnIndicator.setY( (board.tiles.length*Constants.TILE_SIZE) + 2 );
        turnIndicator.setWidth(27);
        turnIndicator.setHeight(27);
        turnIndicator.setFill(Constants.playerOne);
        mainGroup.getChildren().add(turnIndicator);

        //do ui design or dig up the old one we drew years ago
        //board is uhhh 16x16 (x7 possibly if we get this standard board working)
        mainScene = new Scene(mainGroup, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        stageM.setScene(mainScene);


        borderView = new ImageView(FileInput.getImage("Border"));
        borderView.setX(0);
        borderView.setY(board.tiles.length * Constants.TILE_SIZE);
        mainGroup.getChildren().add(borderView);
        turnIndicator.toFront();

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

                if (mouseTileY < 16) {
                    selectedTile = board.tiles[mouseTileX][mouseTileY];
                }

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

                if (mouseTileY < 16) {
                    movingTile = board.tiles[mouseTileX][mouseTileY];
                }

                if (selectedTile != null && movingTile != selectedTile && selectedTile.occupiedBy != Constants.NO_PIECE) {
                    board.highlights[selectedTile.x / Constants.TILE_SIZE][selectedTile.y / Constants.TILE_SIZE].setHighlight(Constants.HighlightType.NONE);

                    if (isValidMove(movingTile)) {

                        if (movingTile.occupiedBy != Constants.NO_PIECE) {
                            Piece remove = board.pieceAt(movingTile.x/Constants.TILE_SIZE, movingTile.y/Constants.TILE_SIZE);
                            if (Constants.PLAYER_ONE.equals(remove.owner)) {
                                GameController.playerOnePieces.remove(remove);
                            } else if (Constants.PLAYER_TWO.equals(remove.owner)) {
                                GameController.playerTwoPieces.remove(remove);
                            } else if (Constants.PLAYER_THREE.equals(remove.owner)) {
                                GameController.playerThreePieces.remove(remove);
                            }
                            board.tiles[movingTile.x/Constants.TILE_SIZE][movingTile.y/Constants.TILE_SIZE].occupiedBy = Constants.NO_PIECE;
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

    public static void main(String[] args) {
        launch();
    }
}