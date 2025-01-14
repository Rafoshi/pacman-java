package model;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class PacMan extends JPanel {
    private int rowCount = 21;
    private int columnCount = 19;
    private int tileSize = 32;
    private int boardWidth = columnCount * tileSize;
    private int boardHeight = rowCount * tileSize;

    private Image wallImage;
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;

    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    private HashSet<Block> walls;
    private HashSet<Block> foods;
    private HashSet<Block> ghosts;
    private Block pacman;

    public PacMan() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);

        wallImage = new ImageIcon(getClass().getResource("/assets/wall.png")).getImage();

        blueGhostImage = new ImageIcon(getClass().getResource("/assets/blueGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("/assets/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("/assets/pinkGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("/assets/redGhost.png")).getImage();

        pacmanUpImage = new ImageIcon(getClass().getResource("/assets/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("/assets/pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("/assets/pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("/assets/pacmanRight.png")).getImage();

        loadMap();
    }

    public void loadMap() {
        walls = new HashSet<Block>();
        foods = new HashSet<Block>();
        ghosts = new HashSet<Block>();

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);

                int x = c * tileSize;
                int y = r * tileSize;

                if (tileMapChar == 'X') {
                    Block wall = new Block(x, y, tileSize, tileSize, wallImage);
                    walls.add(wall);
                } else if (tileMapChar == 'b') {
                    Block blueGhost = new Block(x, y, tileSize, tileSize, blueGhostImage);
                    walls.add(blueGhost);
                } else if (tileMapChar == 'o') {
                    Block orangeGhost = new Block(x, y, tileSize, tileSize, orangeGhostImage);
                    walls.add(orangeGhost);
                } else if (tileMapChar == 'p') {
                    Block pinkGhost = new Block(x, y, tileSize, tileSize, pinkGhostImage);
                    walls.add(pinkGhost);
                } else if (tileMapChar == 'r') {
                    Block redGhost = new Block(x, y, tileSize, tileSize, redGhostImage);
                    walls.add(redGhost);
                } else if (tileMapChar == 'P') {
                    pacman = new Block(x, y, tileSize, tileSize, pacmanRightImage);
                    walls.add(pacman);
                } else if (tileMapChar == ' ') {
                    Block food = new Block(x + 14, y + 14, 4, 4, null);
                    foods.add(food);
                }

            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

        for (Block ghost : ghosts) {
            g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }

        for (Block wall : walls) {
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }

        g.setColor(Color.WHITE);
        for (Block food : foods) {
            g.fillRect(food.x, food.y, food.width, food.height);
        }
    }
}
