package com.github.jingerjesus.thehellchess.game;

import com.github.jingerjesus.thehellchess.control.Constants;
import com.github.jingerjesus.thehellchess.peripherals.FileInput;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Piece {
    public Constants.PieceType type;
    private Player owner;
    public Image cover;
    public int x, y;
    private String coverIdentifier = "";
    private boolean hasPawnMoves = false, hasRookMoves = false, hasKnightMoves = false,
            hasBishopMoves = false, hasKingMoves = false;
        //ignoring Queen because her moves are an addition of Rook and Bishop.

    public Piece(Constants.PieceType type, int x, int y, Player owner) {
        this.type = type;
        this.x = x; this.y = y;

        this.owner = owner;

        if (type.equals(Constants.PieceType.NONE)) {
            coverIdentifier = "Tile";
            this.cover = FileInput.getImage(coverIdentifier);
        } else {

            //adds the color to the name of the png to fetch
            coverIdentifier += this.owner.color;

            //allots movesets per type
            //also adds the piece type to the name of the png to fetch
            switch (type) {
                case KING -> {
                    coverIdentifier += "King";
                    hasKingMoves = true;
                    break;
                }
                case PAWN -> {
                    coverIdentifier += "Pawn";
                    hasPawnMoves = true;
                    break;
                }
                case ROOK -> {
                    coverIdentifier += "Rook";
                    hasRookMoves = true;
                    break;
                }
                case QUEEN -> {
                    coverIdentifier += "Queen";
                    hasRookMoves = true;
                    hasBishopMoves = true;
                    break;
                }
                case BISHOP -> {
                    coverIdentifier += "Bishop";
                    hasBishopMoves = true;
                    break;
                }
                case KNIGHT -> {
                    coverIdentifier += "Knight";
                    hasKnightMoves = true;
                    break;
                }
                default -> {
                    System.out.println("Piece at " + this.x + ", " + this.y + "received unknown piece type.");
                    hasPawnMoves = true;
                    hasRookMoves = true;
                    hasKnightMoves = true;
                    hasBishopMoves = true;
                    hasKingMoves = true;
                }
            }
        }

        this.cover = FileInput.getImage(coverIdentifier);
        //System.out.println(coverIdentifier); //cover identifier is correct

    }

    public int[] addRookMoves(Tile[][] board) {
        //start at x and y
        //get distance to edge to not IOOB
        //iterate thru ray in each direction
        //return 4 ints representing each cardinal direction
        // 0 = North / 1 = East / 2 = South / 3 = West
        int[] possibleMoves = new int[] {0, 0, 0, 0};

        //North: y --
        //East: x ++
        //South: y ++
        //West: x --

        for (int i = 1; i < board.length; i ++) {
            if (this.y - i > 0) {
                if (board[this.x][this.y-i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[0] ++;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.x + i < board.length) {
                if (board[this.x + i][this.y].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[1] ++;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.y + i < board.length) {
                if (board[this.x][this.y + i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[2] ++;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.x - i > 0) {
                if (board[this.x - i][this.y].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[3] ++;
                } else break;
            } else break;
        }

        /*
        for (int i = 0; i < possibleMoves.length; i ++) {
            System.out.println(possibleMoves[i]);
        }
        */

        return possibleMoves;
    }

    public int[] addBishopMoves(Tile[][] board) {

        int[] possibleMoves = new int[] {0, 0, 0, 0};

        for (int i = 1; i < board.length; i ++) {
            if (this.y - i > 0 && this.x + i < board.length) {
                if (board[this.x+i][this.y-i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[0] ++;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.x + i < board.length && y + i < board.length) {
                if (board[this.x + i][this.y + i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[1] ++;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.y + i < board.length && this.x - i > 0) {
                if (board[this.x - i][this.y + i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[2] ++;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.x - i > 0 && this.y - i < board.length) {
                if (board[this.x - i][this.y - i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[3] ++;
                } else break;
            } else break;
        }


        for (int i = 0; i < possibleMoves.length; i ++) {
            System.out.println(possibleMoves[i]);
        }

        return possibleMoves;
    }

    public boolean[] addKingMoves(Tile[][] board) {
        boolean[] possibleMoves = new boolean[] {false, false, false, false, false, false, false, false};

        //indexes start at due north and rotate clockwise around the king.

        if (this.y - 1 > 0) {

        }

        if (this.y + 1 < board.length) {

        }

        return possibleMoves;
    }

    public String toString() {
        return type + " " + owner;
    }
}
