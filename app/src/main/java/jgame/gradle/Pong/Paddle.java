package jgame.gradle.Pong;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

    private int id;
    private int yVelocity;
    private int speed = 10;
    private int arriba;
    private int abajo;
    private RWproperties prop = new RWproperties();

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {

        switch (id) {

            case 1:
                if (prop.readProperties("TeclasJ1").equals("w - s")) {
                    this.arriba = KeyEvent.VK_W;
                    this.abajo = KeyEvent.VK_S;
                } else {
                    this.arriba = KeyEvent.VK_E;
                    this.abajo = KeyEvent.VK_D;
                }

                if (e.getKeyCode() == arriba) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == abajo) {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:

                if (prop.readProperties("TeclasJ2").equals("UP - DOWN")) {
                    this.arriba = KeyEvent.VK_UP;
                    this.abajo = KeyEvent.VK_DOWN;
                } else {
                    this.arriba = KeyEvent.VK_O;
                    this.abajo = KeyEvent.VK_L;
                }

                if (e.getKeyCode() == arriba) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == abajo) {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == arriba) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == abajo) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == arriba) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == abajo) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y = y + yVelocity;
    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);

    }
}
