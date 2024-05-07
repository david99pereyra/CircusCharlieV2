package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class CalderoDeFuego extends ObjetoGrafico{
    BufferedImage Caldero;
    private double posX;
    private int posY;
    
    public CalderoDeFuego(String filename){
        super(filename);
        try{
            this.Caldero = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/fuego1.png")));
            
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    public void setPosition(double x, int y){
        this.posX = x;
        this.posY = y;
    }

    public double getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        g.drawImage(Caldero, (int)getPosX(), getPosY(), null);
    }
}