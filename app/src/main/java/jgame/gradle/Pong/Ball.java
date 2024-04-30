package jgame.gradle.Pong;

import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {

    private Random random;
    private int yVelocity;
    private int xVelocity;
    private int initialSpeed = 2;

    public int getYVelocity() {
        return this.yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getXVelocity() {
        return this.xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getInitialSpeed() {
        return this.initialSpeed;
    }

    public void setInitialSpeed(int initialSpeed) {
        this.initialSpeed = initialSpeed;
    }
    
    public Ball(int x, int y, int width, int height){
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0){
            randomXDirection--;
        }
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0){
            randomYDirection--;
        }
        setYDirection(randomYDirection * initialSpeed);
    }
    
    public void setXDirection(int ramdomXDirection){
        xVelocity = ramdomXDirection;
    }

    public void setYDirection(int ramdomYDirection){
        yVelocity = ramdomYDirection;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }
}
