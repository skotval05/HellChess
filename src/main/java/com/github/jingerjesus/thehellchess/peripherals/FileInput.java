package com.github.jingerjesus.thehellchess.peripherals;

import com.github.jingerjesus.thehellchess.control.Constants;
import com.github.jingerjesus.thehellchess.game.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileInput {
    public static Image getImage(String imageName){

        Image image = null;

        try {
            if (imageName.equals("Tile")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/Tile.png"));

            } else if (imageName.equals("RedPawn")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileRedPawn.png"));
            } else if (imageName.equals("BluePawn")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileBluePawn.png"));
            } else if (imageName.equals("GreenPawn")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileGreenPawn.png"));

            } else if (imageName.equals("RedRook")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileRedRook.png"));
            } else if (imageName.equals("BlueRook")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileBlueRook.png"));
            } else if (imageName.equals("GreenRook")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileGreenRook.png"));

            } else if (imageName.equals("RedKnight")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileRedKnight.png"));
            } else if (imageName.equals("BlueKnight")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileBlueKnight.png"));
            } else if (imageName.equals("GreenKnight")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileGreenKnight.png"));

            } else if (imageName.equals("RedBishop")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileRedBishop.png"));
            } else if (imageName.equals("BlueBishop")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileBlueBishop.png"));
            } else if (imageName.equals("GreenBishop")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileGreenBishop.png"));

            } else if (imageName.equals("RedQueen")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileRedQueen.png"));
            } else if (imageName.equals("BlueQueen")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileBlueQueen.png"));
            } else if (imageName.equals("GreenQueen")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileGreenQueen.png"));

            } else if (imageName.equals("RedKing")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileRedKing.png"));
            } else if (imageName.equals("BlueKing")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileBlueKing.png"));
            } else if (imageName.equals("GreenKing")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/TileGreenKing.png"));

            } else if (imageName.equals("Empty")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/SelectNone.png"));
            } else if (imageName.equals("Highlight")) {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/SelectHighlight.png"));
            } else {
                image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/Tile.png"));
            }


        } catch (FileNotFoundException e) {e.printStackTrace();}

        return image;
    }

    public static ImageView selectTile(Tile selectedTile) {
        Image image;
        ImageView view = null;

        try {
            image = new Image(new FileInputStream("src/main/java/com/github/jingerjesus/thehellchess/resources/SelectHighlight.png"));
            view = new ImageView(image);
            view.setX(selectedTile.x * Constants.TILE_SIZE);
            view.setY(selectedTile.y * Constants.TILE_SIZE);
            view.setFitHeight(Constants.TILE_SIZE);
            view.setFitWidth(Constants.TILE_SIZE);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return view;
    }


}
