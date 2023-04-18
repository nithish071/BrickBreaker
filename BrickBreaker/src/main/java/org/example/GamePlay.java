package org.example;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.PrivateKey;
import java.sql.Time;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(delay,this);
        Timer.start();

    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        map.draw((Graphics2D) g);
        // border
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(683,0,3,592);

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString("" + score, 590, 30);

        // Slider
        g.setColor(Color.yellow);
        g.fillRect(playerX,550,100,8);

        //ball
        g.setColor(Color.green);
        g.fillOval(ballPosX,ballPosY,20,20);

        if(ballPosY > 570) {
            play =false;
            ballXdir = 0; // refresh
            ballYdir = 0; // refresh
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    Game Over Score: " + score, 190, 300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("  Press Enter to Restart" + score, 190, 340);
        }

        if(totalBricks == 0 ) {
            play = false;
            ballXdir = -2; // refresh
            ballYdir = -1       ; // refresh
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    Game Over Score: " + score, 190, 300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("  Press Enter to Restart", 190, 340);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();

        if(play) {
            if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8))) {
                ballYdir = - ballYdir;
            }

            A:
            for(int i = 0; i < map.map.length; i++) {
                for(int j = 0; j < map.map[0].length; j++) {
                    if(map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeigth + 50;
                        int bricksWidth = map.brickWidth;
                        int brickHeight = map.brickHeigth;

                        Rectangle rect = new Rectangle(brickX,brickY,bricksWidth,brickHeight);
                        Rectangle ballrect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickrect = rect;

                        if(ballrect.intersects(brickrect)) {
                            map.setBricksValue(0,i,j);
                            totalBricks--;
                            score += 5;
                            if(ballPosX + 19 <= brickrect.x || ballPosX + 1 >= brickrect.x  + bricksWidth) {
                                ballXdir =  -ballXdir;
                            } else {
                                ballYdir = - ballYdir;
                            }
                            break A;
                        }
                    }
                }

            }
            ballPosX += ballXdir;
            ballPosY += ballYdir;
            if(ballPosX < 0) {
                ballXdir = -ballXdir;
            }
            if(ballPosY < 0) {
                ballYdir = -ballYdir;
            }
            if(ballPosY > 670) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX < 10) {
                playerX = 10;
            } else {
                moveLeft ();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play) {
                ballPosX= 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalBricks =21;
                map = new MapGenerator(3,7);
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight () {
        play = true;
        playerX += 20;
    }
    public void moveLeft() {
        play =true;
        playerX -= 20;
    }
}
