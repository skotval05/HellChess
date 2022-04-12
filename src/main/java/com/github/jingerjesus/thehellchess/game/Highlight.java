package com.github.jingerjesus.thehellchess.game;

import com.github.jingerjesus.thehellchess.control.Constants;
import com.github.jingerjesus.thehellchess.control.UI;
import com.github.jingerjesus.thehellchess.peripherals.FileInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Highlight {
    public ImageView view;
    public Image image;
    public int x, y;
    public double transparency;
    public Constants.HighlightType type;

    public Highlight(int x, int y, Constants.HighlightType type) {
        this.x = x;
        this.y = y;
        this.type = type;

        switch(type) {
            case SELECTED -> {
                transparency = 0.33;
                image = FileInput.getImage("Highlight");
            }
            case MOVESPACE -> {
                transparency = 0.33;
                image = FileInput.getImage("Movable");
            }
            case CAPTURESPACE -> {
                transparency = 0.25;
                image = FileInput.getImage("Highlight");
            }
            default -> {
                transparency = 0.0;
                image = FileInput.getImage("Empty");
            }
        }

        view = new ImageView(image);
        view.setOpacity(transparency);
        view.setX(x * Constants.TILE_SIZE);
        view.setY(y * Constants.TILE_SIZE);
        view.setFitHeight(Constants.TILE_SIZE);
        view.setFitWidth(Constants.TILE_SIZE);
    }

    public void setHighlight(Constants.HighlightType newType) {
        switch(newType) {
            case SELECTED -> {
                transparency = 0.33;
                image = FileInput.getImage("Highlight");
            }
            case MOVESPACE -> {
                transparency = 0.33;
                image = FileInput.getImage("Movable");
            }
            case CAPTURESPACE -> {
                transparency = 0.25;
                image = FileInput.getImage("Highlight");
            }
            default -> {
                transparency = 0.0;
                image = FileInput.getImage("Empty");
            }
        }
    }
}
