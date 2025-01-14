package model;

import java.awt.*;

public class Block {
    int x;
    int y;
    int width;
    int height;
    Image image;

    int startX;
    int startY;

    public Block(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;

        this.startX = x;
        this.startY = y;
    }
}
