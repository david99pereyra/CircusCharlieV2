package jgame.gradle.Pong;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.image.*;
import java.awt.*;

public class Raqueta extends ObjetoGrafico{
    private int width = 10;
    private int height = 80;
    private double speed = 4;
    private int id;

    public Raqueta(int x, int y, int nroJugador, Color color){
        this.positionX = x;
        this.positionY = y;
        this.id = nroJugador;
        this.setHeight(80);
        this.setWidth(10);
        this.imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = this.imagen.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
    }
    public void up(){
        this.positionY -= speed;
    }
    public void down(){
        this.positionY += speed;
    }

    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) positionX, (int) positionY, null);
    }
}
