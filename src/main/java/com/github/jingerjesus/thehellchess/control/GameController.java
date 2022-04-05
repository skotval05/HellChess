package com.github.jingerjesus.thehellchess.control;

import java.util.ArrayList;

import com.github.jingerjesus.thehellchess.game.Piece;
import com.github.jingerjesus.thehellchess.game.Player;

/*
    IMPORTANT NOTE:
    RED = PLAYER ONE
    BLUE = PLAYER TWO
    GREEN = PLAYER THREE
*/

public class GameController {
    private Player turn = Constants.PLAYER_ONE;

    public static ArrayList<Piece> playerOnePieces = new ArrayList<Piece>(32);
    public static ArrayList<Piece> playerTwoPieces = new ArrayList<Piece>(32);
    public static ArrayList<Piece> playerThreePieces = new ArrayList<Piece>(32);



    public void cycleTurn() {
        if (turn == Constants.PLAYER_ONE) turn = Constants.PLAYER_TWO;
        else if (turn == Constants.PLAYER_TWO) turn = Constants.PLAYER_THREE;
        else if (turn == Constants.PLAYER_THREE) turn = Constants.PLAYER_ONE;
        else turn = Constants.NO_PLAYER;
    }

    public static void initPieces(boolean normalStart) {
        // if (normalStart) {
            for (int i = 0; i < 16; i ++) {
                playerOnePieces.add(i, new Piece(Constants.PieceType.PAWN, i, 14, Constants.PLAYER_ONE));
                playerTwoPieces.add(i, new Piece(Constants.PieceType.PAWN, i, 1, Constants.PLAYER_TWO));
            }

            for (int i = 0; i < 8; i ++) {
                playerThreePieces.add(new Piece(Constants.PieceType.PAWN, 1, i + 4, Constants.PLAYER_THREE));
                playerThreePieces.add(new Piece(Constants.PieceType.PAWN, 14, i + 4, Constants.PLAYER_THREE));
            }

            //beautiful code incoming

            playerOnePieces.add(new Piece(Constants.PieceType.ROOK, 0, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.KNIGHT, 1, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.BISHOP, 2, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.QUEEN, 3, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.KING, 4, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.BISHOP, 5, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.KNIGHT, 6, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.ROOK, 7, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.ROOK, 8, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.KNIGHT, 9, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.BISHOP, 10, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.KING, 11, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.QUEEN, 12, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.BISHOP, 13, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.KNIGHT, 14, 15, Constants.PLAYER_ONE));
            playerOnePieces.add(new Piece(Constants.PieceType.ROOK, 15, 15, Constants.PLAYER_ONE));



            playerTwoPieces.add(new Piece(Constants.PieceType.ROOK, 0, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.KNIGHT, 1, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.BISHOP, 2, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.QUEEN, 3, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.KING, 4, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.BISHOP, 5, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.KNIGHT, 6, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.ROOK, 7, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.ROOK, 8, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.KNIGHT, 9, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.BISHOP, 10, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.KING, 11, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.QUEEN, 12, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.BISHOP, 13, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.KNIGHT, 14, 0, Constants.PLAYER_TWO));
            playerTwoPieces.add(new Piece(Constants.PieceType.ROOK, 15, 0, Constants.PLAYER_TWO));



            playerThreePieces.add(new Piece(Constants.PieceType.ROOK, 0, 4, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.KNIGHT, 0, 5, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.BISHOP, 0, 6, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.QUEEN, 0, 7, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.KING, 0, 8, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.BISHOP, 0, 9, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.KNIGHT, 0, 10, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.ROOK, 0, 11, Constants.PLAYER_THREE));

            playerThreePieces.add(new Piece(Constants.PieceType.ROOK, 15, 4, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.KNIGHT, 15, 5, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.BISHOP, 15, 6, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.KING, 15, 8, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.QUEEN, 15, 7, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.BISHOP, 15, 9, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.KNIGHT, 15, 10, Constants.PLAYER_THREE));
            playerThreePieces.add(new Piece(Constants.PieceType.ROOK, 15, 11, Constants.PLAYER_THREE));





        // }
    }

}
