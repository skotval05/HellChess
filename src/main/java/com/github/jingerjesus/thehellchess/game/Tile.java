package com.github.jingerjesus.thehellchess.game;

import com.github.jingerjesus.thehellchess.control.Constants;
import com.github.jingerjesus.thehellchess.control.GameController;
import com.github.jingerjesus.thehellchess.peripherals.FileInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class Tile {
    public Image cover;
    public ImageView coverView;
    public Piece occupiedBy;
    public int x;
    public int y;

    public Tile(int x, int y, Piece occupiedBy) {

        if (occupiedBy.equals(Constants.NO_PIECE)) {
            this.cover = FileInput.getImage("Tile");
        } else {
            this.cover = occupiedBy.cover;
        }

        this.x = x;
        this.y = y;
        this.occupiedBy = occupiedBy;
        coverView = new ImageView(cover);
        coverView.setX(this.x);
        coverView.setY(this.y);
        coverView.setFitWidth(Constants.TILE_SIZE);
        coverView.setFitHeight(Constants.TILE_SIZE);
    }

    /*
    public ImageView updateTile(Board board) {
        //we know where we are
        //see if a piece is here and get its data
        //convert data into png name data and replace cover

        if (occupiedBy.equals(Constants.NO_PIECE)) {
            this.cover = FileInput.getImage("Tile");
        } else {
            this.cover = occupiedBy.cover;
        }

        this.occupiedBy = board.pieceAt(this.x, this.y);
        System.out.println(occupiedBy);



        coverView.setImage(occupiedBy.cover);
        coverView.toFront();

        coverView.setX(this.x);
        coverView.setY(this.y);
        coverView.setFitWidth(Constants.TILE_SIZE);
        coverView.setFitHeight(Constants.TILE_SIZE);


        return this.coverView;
    }
    */

}
