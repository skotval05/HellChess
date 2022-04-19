package com.github.jingerjesus.thehellchess.game;

import com.github.jingerjesus.thehellchess.control.Constants;
import com.github.jingerjesus.thehellchess.control.GameController;
import com.github.jingerjesus.thehellchess.control.UI;
import com.github.jingerjesus.thehellchess.game.Highlight;


public class Board {
    public Tile[][] tiles = new Tile[16][16];
    public Highlight[][] highlights = new Highlight[16][16];

    public Board() {
        for (int i = 0; i < tiles.length; i ++) {
            for (int j = 0; j < tiles[i].length; j ++) {

                //tilemap init
                tiles[i][j] = new Tile(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE, Constants.NO_PIECE);
                UI.mainGroup.getChildren().add(tiles[i][j].coverView);

                //highlight map init
                highlights[i][j] = new Highlight(i , j,
                        Constants.HighlightType.NONE);
                UI.mainGroup.getChildren().add(highlights[i][j].view);
            }
        }
    }

    public void updateBoard() {

        for (int i = 0; i < tiles.length; i ++) {
            for (int j = 0; j < tiles[i].length; j ++) {

                for (int k = 0; k < GameController.playerOnePieces.size(); k ++) {
                    if (GameController.playerOnePieces.get(k).x == i && GameController.playerOnePieces.get(k).y == j) {
                        tiles[i][j].coverView.setImage(GameController.playerOnePieces.get(k).cover);
                        tiles[i][j].occupiedBy = GameController.playerOnePieces.get(k);
                    }
                }

                for (int k = 0; k < GameController.playerTwoPieces.size(); k ++) {
                    if (GameController.playerTwoPieces.get(k).x == i && GameController.playerTwoPieces.get(k).y == j) {
                        tiles[i][j].coverView.setImage(GameController.playerTwoPieces.get(k).cover);
                        tiles[i][j].occupiedBy = GameController.playerTwoPieces.get(k);
                    }
                }

                for (int k = 0; k < GameController.playerThreePieces.size(); k ++) {
                    if (GameController.playerThreePieces.get(k).x == i && GameController.playerThreePieces.get(k).y == j) {
                        tiles[i][j].coverView.setImage(GameController.playerThreePieces.get(k).cover);
                        tiles[i][j].occupiedBy = GameController.playerThreePieces.get(k);
                    }
                }
            }
        }
    }

    public void updateHighlights(Tile selectedTile) {
        if (selectedTile != null) {
            highlights[selectedTile.x][selectedTile.y].setHighlight(Constants.HighlightType.SELECTED);
        } else {
            for (int i = 0; i < highlights.length; i ++) {
                for (int j = 0; j < highlights.length; j ++) {
                    highlights[i][j].setHighlight(Constants.HighlightType.NONE);
                }
            }
        }
    }

    public void showMoves(int[] possibleMoves, Piece piece) {
        this.removeHighlights();
        switch (piece.type) {
            case KNIGHT -> {
                if (possibleMoves[0] == 1) {
                    highlights[piece.x+1][piece.y-2].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[1] == 1) {
                    highlights[piece.x+2][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[2] == 1) {
                    highlights[piece.x+2][piece.y+1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[3] == 1) {
                    highlights[piece.x+1][piece.y+2].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[4] == 1) {
                    highlights[piece.x-1][piece.y+2].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[5] == 1) {
                    highlights[piece.x-2][piece.y+1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[6] == 1) {
                    highlights[piece.x-2][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[7] == 1) {
                    highlights[piece.x-1][piece.y-2].setHighlight(Constants.HighlightType.MOVESPACE);
                }

            }
            case BISHOP -> {
                for (int i = 1; i <= possibleMoves[0]; i ++) {
                    highlights[piece.x+i][piece.y-i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[1]; i ++) {
                    highlights[piece.x+i][piece.y+i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[2]; i ++) {
                    highlights[piece.x-i][piece.y+i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[3]; i ++) {
                    highlights[piece.x-i][piece.y-i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
            }
            case QUEEN -> {
                for (int i = 1; i <= possibleMoves[0]; i ++) {
                    highlights[piece.x][piece.y - i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[1]; i ++) {
                    highlights[piece.x+i][piece.y].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[2]; i ++) {
                    highlights[piece.x][piece.y + i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[3]; i ++) {
                    highlights[piece.x-i][piece.y].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[4]; i ++) {
                    highlights[piece.x+i][piece.y-i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[5]; i ++) {
                    highlights[piece.x+i][piece.y+i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[6]; i ++) {
                    highlights[piece.x-i][piece.y+i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[7]; i ++) {
                    highlights[piece.x-i][piece.y-i].setHighlight(Constants.HighlightType.MOVESPACE);
                }

            }
            case KING -> {
                if (possibleMoves[0] == 1) {
                    highlights[piece.x][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[1] == 1) {
                    highlights[piece.x+1][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[2] == 1) {
                    highlights[piece.x+1][piece.y].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[3] == 1) {
                    highlights[piece.x][piece.y+1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[4] == 1) {
                    highlights[piece.x+1][piece.y+1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[5] == 1) {
                    highlights[piece.x-1][piece.y+1].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[6] == 1) {
                    highlights[piece.x-1][piece.y].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                if (possibleMoves[7] == 1) {
                    highlights[piece.x-1][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                }

            }
            case PAWN -> {
                if (piece.owner == Constants.PLAYER_ONE) {
                    for (int i = 1; i <= possibleMoves[0]; i ++) {
                        highlights[piece.x][piece.y - i].setHighlight(Constants.HighlightType.MOVESPACE);
                    }
                    if (possibleMoves[1] == 1) {
                        highlights[piece.x-1][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                    }
                    if (possibleMoves[2] == 1) {
                        highlights[piece.x+1][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                    }
                } else if (piece.owner == Constants.PLAYER_TWO) {
                    for (int i = 1; i <= possibleMoves[0]; i ++) {
                        highlights[piece.x][piece.y + i].setHighlight(Constants.HighlightType.MOVESPACE);
                    }
                    if (possibleMoves[1] == 1) {
                        highlights[piece.x+1][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                    }
                    if (possibleMoves[2] == 1) {
                        highlights[piece.x-1][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                    }
                } else if (piece.owner == Constants.PLAYER_THREE) {
                    if (piece.firstX == 1) {
                        for (int i = 1; i <= possibleMoves[0]; i ++) {
                            highlights[piece.x+i][piece.y].setHighlight(Constants.HighlightType.MOVESPACE);
                        }
                        if (possibleMoves[1] == 1) {
                            highlights[piece.x+1][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                        }
                        if (possibleMoves[2] == 1) {
                            highlights[piece.x+1][piece.y+1].setHighlight(Constants.HighlightType.MOVESPACE);
                        }
                    } else if (piece.firstX == 14) {
                        for (int i = 1; i <= possibleMoves[0]; i ++) {
                            highlights[piece.x-i][piece.y].setHighlight(Constants.HighlightType.MOVESPACE);
                        }
                        if (possibleMoves[1] == 1) {
                            highlights[piece.x+1][piece.y+1].setHighlight(Constants.HighlightType.MOVESPACE);
                        }
                        if (possibleMoves[2] == 1) {
                            highlights[piece.x+1][piece.y-1].setHighlight(Constants.HighlightType.MOVESPACE);
                        }
                    }
                }
            }
            case ROOK -> {
                for (int i = 1; i <= possibleMoves[0]; i ++) {
                    highlights[piece.x][piece.y - i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[1]; i ++) {
                    highlights[piece.x+i][piece.y].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[2]; i ++) {
                    highlights[piece.x][piece.y + i].setHighlight(Constants.HighlightType.MOVESPACE);
                }
                for (int i = 1; i <= possibleMoves[3]; i ++) {
                    highlights[piece.x-i][piece.y].setHighlight(Constants.HighlightType.MOVESPACE);
                }
            }
            case NONE -> System.out.println("No Piece to show moves.");
        }
    }

    public void removeHighlights() {
        for (int i = 0; i < tiles.length; i ++) {
            for (int j = 0; j < tiles.length; j ++) {
                highlights[i][j].setHighlight(Constants.HighlightType.NONE);
            }
        }
    }

    private Piece pieceAt(int x, int y) {
        if (this.tiles[x][y].occupiedBy.equals(Constants.NO_PIECE))
            System.out.println("No Piece Here");
        return this.tiles[x][y].occupiedBy;
    }

}
