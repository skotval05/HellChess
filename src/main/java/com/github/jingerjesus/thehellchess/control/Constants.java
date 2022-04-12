package com.github.jingerjesus.thehellchess.control;

import com.github.jingerjesus.thehellchess.game.Piece;
import com.github.jingerjesus.thehellchess.game.Player;

public class Constants {

    //Player constants
    public static final Player NO_PLAYER = new Player(0);
    public static final Player PLAYER_ONE = new Player(1);
    public static final Player PLAYER_TWO = new Player(2);
    public static final Player PLAYER_THREE = new Player(3);

    //UI constants
    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;

    //Board/tile constants
    public static final int TILE_SIZE = 32;
    public static final Piece NO_PIECE = new Piece(PieceType.NONE, -1, -1, NO_PLAYER);

    public static enum PieceType {
        PAWN, ROOK, KNIGHT, BISHOP,
        KING, QUEEN, NONE
    }

    public static enum HighlightType {
        SELECTED, MOVESPACE, CAPTURESPACE, NONE
    }



}
