package com.github.jingerjesus.thehellchess.game;

import com.github.jingerjesus.thehellchess.control.Constants;
import com.github.jingerjesus.thehellchess.control.GameController;
import com.github.jingerjesus.thehellchess.peripherals.FileInput;
import javafx.scene.image.Image;


public class Piece {
    public Constants.PieceType type;
    public boolean isFirstTurn = true;
    public Player owner;
    public Image cover;
    public int x, y;
    public int firstX;
    private String coverIdentifier = "";
    public int[][] moveSets;
    private boolean hasPawnMoves = false, hasRookMoves = false, hasKnightMoves = false,
            hasBishopMoves = false, hasKingMoves = false;
        //ignoring Queen because her moves are an addition of Rook and Bishop.

    public Piece(Constants.PieceType type, int x, int y, Player owner) {
        this.type = type;
        this.x = x; this.y = y;
        this.firstX = x;

        this.owner = owner;

        if (type.equals(Constants.PieceType.NONE)) {
            coverIdentifier = "Tile";
            this.cover = FileInput.getImage(coverIdentifier);
        } else {

            //adds the color to the name of the png to fetch
            coverIdentifier += this.owner.color;

            //allots move sets per type
            //also adds the piece type to the name of the png to fetch
            switch (type) {
                case KING -> {
                    coverIdentifier += "King";
                    hasKingMoves = true;
                }
                case PAWN -> {
                    coverIdentifier += "Pawn";
                    hasPawnMoves = true;
                }
                case ROOK -> {
                    coverIdentifier += "Rook";
                    hasRookMoves = true;
                }
                case QUEEN -> {
                    coverIdentifier += "Queen";
                    hasRookMoves = true;
                    hasBishopMoves = true;
                }
                case BISHOP -> {
                    coverIdentifier += "Bishop";
                    hasBishopMoves = true;
                }
                case KNIGHT -> {
                    coverIdentifier += "Knight";
                    hasKnightMoves = true;
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

    public int[] addMovesOfType(Constants.PieceType type, Tile[][] board) {
        switch (type) {
            case ROOK -> {
                return this.addRookMoves(board);
            }
            case QUEEN -> {
                int[] a1 = this.addRookMoves(board);
                int[] a2 = this.addBishopMoves(board);
                int[] temp = new int[] {a1[0], a1[1], a1[2], a1[3], a2[0], a2[1], a2[2], a2[3]};
                return temp;
            }
            case BISHOP -> {
                return this.addBishopMoves(board);
            }
            case PAWN -> {
                return this.addPawnMoves(board);
            }
            case KING -> {
                return this.addKingMoves(board);
            }
            case KNIGHT -> {
                return this.addKnightMoves(board);
            }
        }
        return new int[] {0};
    }

    private int[] addRookMoves(Tile[][] board) {
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
            if (this.y - i >= 0) {
                if (board[this.x][this.y-i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[0] ++;
                } else if (board[this.x][this.y-i].occupiedBy.owner != GameController.turn) {
                    possibleMoves[0] ++; break;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.x + i < board.length) {
                if (board[this.x + i][this.y].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[1] ++;
                } else if (board[this.x + i][this.y].occupiedBy.owner != GameController.turn) {
                    possibleMoves[1] ++; break;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.y + i < board.length) {
                if (board[this.x][this.y + i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[2] ++;
                } else if (board[this.x][this.y+i].occupiedBy.owner != GameController.turn) {
                    possibleMoves[2] ++; break;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.x - i >= 0) {
                if (board[this.x - i][this.y].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[3] ++;
                } else if (board[this.x - i][this.y].occupiedBy.owner != GameController.turn) {
                    possibleMoves[3] ++; break;
                } else break;
            } else break;
        }

        // /*
        for (int i = 0; i < possibleMoves.length; i ++) {
            System.out.println(possibleMoves[i]);
        }
        // */

        return possibleMoves;
    }

    private int[] addBishopMoves(Tile[][] board) {

        int[] possibleMoves = new int[] {0, 0, 0, 0};

        for (int i = 1; i < board.length; i ++) {
            if (this.y - i >= 0 && this.x + i < board.length) {
                if (board[this.x+i][this.y-i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[0] ++;
                } else if (board[this.x + i][this.y-i].occupiedBy.owner != GameController.turn) {
                    possibleMoves[0] ++; break;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.x + i < board.length && y + i < board.length) {
                if (board[this.x + i][this.y + i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[1] ++;
                } else if (board[this.x + i][this.y+1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[1] ++; break;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.y + i < board.length && this.x - i >= 0) {
                if (board[this.x - i][this.y + i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[2] ++;
                } else if (board[this.x - i][this.y+i].occupiedBy.owner != GameController.turn) {
                    possibleMoves[2] ++; break;
                } else break;
            } else break;
        }

        for (int i = 1; i < board.length; i ++) {
            if (this.x - i >= 0 && this.y - i >= 0) {
                if (board[this.x - i][this.y - i].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[3] ++;
                } else if (board[this.x - i][this.y-i].occupiedBy.owner != GameController.turn) {
                    possibleMoves[3] ++; break;
                } else break;
            } else break;
        }

        // /*
        for (int i = 0; i < possibleMoves.length; i ++) {
            System.out.println(possibleMoves[i]);
        }
        // */


        return possibleMoves;
    }

    private int[] addKingMoves(Tile[][] board) {
        int[] possibleMoves = new int[] {0, 0, 0, 0, 0, 0, 0, 0};

        //indexes start at due north and rotate clockwise around the king.

        if (this.y - 1 >= 0) {
            if (board[this.x][this.y - 1].occupiedBy == Constants.NO_PIECE
                        || board[this.x][this.y-1].occupiedBy.owner != GameController.turn) {
                possibleMoves[0] = 1;
            }

            if (this.x + 1 < board.length) {
                if (board[this.x + 1][this.y - 1].occupiedBy == Constants.NO_PIECE
                        || board[this.x+1][this.y-1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[1] = 1;
                }
            }
            if (this.x - 1 >= 0) {
                if (board[this.x-1][this.y-1].occupiedBy == Constants.NO_PIECE
                        || board[this.x-1][this.y-1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[7] = 1;
                }
            }
        }

        if (this.x + 1 < board.length) {
            if (board[this.x+1][this.y].occupiedBy == Constants.NO_PIECE
                        || board[this.x+1][this.y].occupiedBy.owner != GameController.turn) {
                possibleMoves[2] = 1;
            }
        }

        if (this.x - 1 >= 0) {
            if (board[this.x-1][this.y].occupiedBy == Constants.NO_PIECE
                        || board[this.x-1][this.y].occupiedBy.owner != GameController.turn) {
                possibleMoves[6] = 1;
            }
        }


        if (this.y + 1 < board.length) {
            if (board[this.x][this.y + 1].occupiedBy == Constants.NO_PIECE
                        || board[this.x][this.y+1].occupiedBy.owner != GameController.turn) {
                possibleMoves[3] = 1;
            }

            if (this.x + 1 < board.length) {
                if (board[this.x + 1][this.y + 1].occupiedBy == Constants.NO_PIECE
                        || board[this.x+1][this.y+1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[4] = 1;
                }
            }
            if (this.x - 1 >= 0) {
                if (board[this.x-1][this.y+1].occupiedBy == Constants.NO_PIECE
                        || board[this.x-1][this.y+1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[5] = 1;
                }
            }
        }

        /*
        for (int i = 0; i < possibleMoves.length; i ++) {
            System.out.println(possibleMoves[i]);
        }
        */


        return possibleMoves;
    }

    private int[] addKnightMoves(Tile[][] board) {
        int[] possibleMoves = new int[] {0, 0, 0, 0, 0, 0, 0, 0};

        if (this.y - 1 >= 0) {

            if (this.x + 2 < board.length) {
                if (board[this.x+2][this.y-1].occupiedBy == Constants.NO_PIECE
                        || board[this.x+2][this.y-1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[1] = 1;
                }
            }
            if (this.x - 2 >= 0) {
                if (board[this.x-2][this.y-1].occupiedBy == Constants.NO_PIECE
                        || board[this.x-2][this.y-1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[6] = 1;
                }
            }

            if (this.y - 2 >= 0) {
                if (this.x + 1 < board.length) {
                    if (board[this.x + 1][this.y - 2].occupiedBy == Constants.NO_PIECE
                            || board[this.x+1][this.y-2].occupiedBy.owner != GameController.turn) {
                        possibleMoves[0] = 1;
                    }
                }
                if (this.x - 1 >= 0) {
                    if (board[this.x - 1][this.y - 2].occupiedBy == Constants.NO_PIECE
                            || board[this.x-1][this.y-2].occupiedBy.owner != GameController.turn) {
                        possibleMoves[7] = 1;
                    }
                }
            }
        }

        if (this.y + 1 < board.length) {

            if (this.x + 2 < board.length) {
                if (board[this.x+2][this.y+1].occupiedBy == Constants.NO_PIECE
                        || board[this.x+2][this.y+1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[2] = 1;
                }
            }
            if (this.x - 2 >= 0) {
                if (board[this.x-2][this.y+1].occupiedBy == Constants.NO_PIECE
                        || board[this.x-2][this.y+1].occupiedBy.owner != GameController.turn) {
                    possibleMoves[5] = 1;
                }
            }

            if (this.y + 2 < board.length) {

                if (this.x + 1 < board.length) {
                    if (board[this.x + 1][this.y + 2].occupiedBy == Constants.NO_PIECE
                            || board[this.x+1][this.y+2].occupiedBy.owner != GameController.turn) {
                        possibleMoves[3] = 1;
                    }
                }
                if (this.x - 1 >= 0) {
                    if (board[this.x - 1][this.y + 2].occupiedBy == Constants.NO_PIECE
                            || board[this.x-1][this.y+2].occupiedBy.owner != GameController.turn) {
                        possibleMoves[4] = 1;
                    }
                }

            }

        }

        /*
        for (int i = 0; i < possibleMoves.length; i ++) {
            System.out.println(possibleMoves[i]);
        }
        */

        return possibleMoves;
    }

    //oh, god here we go I hate pawns why do you do this
    //why do you have to be special

    public int[] addPawnMoves(Tile[][]board) {
        //possible moves orientation
        // . 0 .
        // 1 0 2
        // . P .

        int[] possibleMoves = new int[] {0, 0, 0};

        if (this.owner == Constants.PLAYER_ONE) {
            if (this.y - 1 >= 0) {
                if (board[this.x][this.y - 1].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[0] = 1;
                    if (isFirstTurn && this.y - 2 > 0) {
                        if (board[this.x][this.y - 2].occupiedBy == Constants.NO_PIECE) {
                            possibleMoves[0] = 2;
                        }
                    }
                }
                if (this.x - 1 >= 0) {
                    if (board[this.x-1][this.y-1].occupiedBy.owner != GameController.turn
                    && board[this.x-1][this.y-1].occupiedBy != Constants.NO_PIECE) {
                        possibleMoves[1] = 1;
                    }
                }
                if (this.x + 1 < board.length) {
                    if (board[this.x+1][this.y-1].occupiedBy.owner != GameController.turn
                            && board[this.x+1][this.y-1].occupiedBy != Constants.NO_PIECE) {
                        possibleMoves[2] = 1;
                    }
                }
            }
        } else if (this.owner == Constants.PLAYER_TWO) {
            if (this.y + 1 < board.length) {
                if (board[this.x][this.y + 1].occupiedBy == Constants.NO_PIECE) {
                    possibleMoves[0] = 1;
                    if (isFirstTurn && this.y + 2 < board.length) {
                        if (board[this.x][this.y + 2].occupiedBy == Constants.NO_PIECE) {
                            possibleMoves[0] = 2;
                        }
                    }
                }
                if (this.x - 1 >= 0) {
                    if (board[this.x-1][this.y+1].occupiedBy.owner != GameController.turn
                            && board[this.x-1][this.y+1].occupiedBy != Constants.NO_PIECE) {
                        possibleMoves[2] = 1;
                    }
                }
                if (this.x + 1 < board.length) {
                    if (board[this.x+1][this.y+1].occupiedBy.owner != GameController.turn
                            && board[this.x+1][this.y+1].occupiedBy != Constants.NO_PIECE) {
                        possibleMoves[1] = 1;
                    }
                }
            }
        } else if (this.owner == Constants.PLAYER_THREE) {
            if (this.firstX == 1) {
                if (this.x + 1 < board.length) {
                    if (board[this.x + 1][this.y].occupiedBy == Constants.NO_PIECE) {
                        possibleMoves[0] = 1;
                        if (isFirstTurn && this.x + 2 < board.length) {
                            if (board[this.x + 2][this.y].occupiedBy == Constants.NO_PIECE) {
                                possibleMoves[0] = 2;
                            }
                        }
                    }
                    if (this.y - 1 >= 0) {
                        if (board[this.x+1][this.y-1].occupiedBy.owner != GameController.turn
                        && board[this.x+1][this.y-1].occupiedBy != Constants.NO_PIECE) {
                            possibleMoves[1] = 1;
                        }
                    }
                    if (this.y + 1 < board.length) {
                        if (board[this.x+1][this.y+1].occupiedBy.owner != GameController.turn
                                && board[this.x+1][this.y+1].occupiedBy != Constants.NO_PIECE) {
                            possibleMoves[2] = 1;
                        }
                    }
                }
            }
            if (this.firstX == 14) {
                if (this.x - 1 >= 0) {
                    if (board[this.x - 1][this.y].occupiedBy == Constants.NO_PIECE) {
                        possibleMoves[0] = 1;
                        if (isFirstTurn && this.x - 2 > 0) {
                            if (board[this.x - 2][this.y].occupiedBy == Constants.NO_PIECE) {
                                possibleMoves[0] = 2;
                            }
                        }
                    }
                    if (this.y - 1 >= 0) {
                        if (board[this.x-1][this.y-1].occupiedBy.owner != GameController.turn
                                && board[this.x-1][this.y-1].occupiedBy != Constants.NO_PIECE) {
                            possibleMoves[2] = 1;
                        }
                    }
                    if (this.y + 1 < board.length) {
                        if (board[this.x-1][this.y+1].occupiedBy.owner != GameController.turn
                                && board[this.x-1][this.y+1].occupiedBy != Constants.NO_PIECE) {
                            possibleMoves[1] = 1;
                        }
                    }
                }
            }
        }

        /*
        for (int i = 0; i < possibleMoves.length; i ++) {
            System.out.println(possibleMoves[i]);
        }
        */

        return possibleMoves;
    }

    public String toString() {
        return type + " " + owner;
    }
}
