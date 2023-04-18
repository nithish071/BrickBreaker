package org.example;
import java.awt.*;

public class MapGenerator {
    public int map[][];
    int brickWidth;
    int brickHeigth;
    public MapGenerator(int row, int col) {
        map = new int[row][col];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        brickWidth = 540 / col;
        brickHeigth = 150 / row;
    }
    public void draw(Graphics2D g) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
               if(map[i][j] > 0) {
                   g.setColor(Color.RED);
                   g.fillRect(j * brickWidth + 80,i * brickHeigth + 50,brickWidth,brickHeigth);
                   g.setStroke(new BasicStroke(3));
                   g.setColor(Color.black);
                   g.drawRect(j * brickWidth + 80,i * brickHeigth + 50,brickWidth,brickHeigth);
               }
            }
        }
    }

    public void setBricksValue(int value,int row,int col) {
        map [row][col] = value;
    }
}
