package com.github.jingerjesus.thehellchess.game;

public class Player {
    String color;

    public Player(int playerNum) {
        if (playerNum == 1) {
            color = "Red";
        } else if (playerNum == 2) {
            color = "Blue";
        } else if (playerNum == 3) {
            color = "Green";
        } else {
            color = "NONE";
        }
    }
}
