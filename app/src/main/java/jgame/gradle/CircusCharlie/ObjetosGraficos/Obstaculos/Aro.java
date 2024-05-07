package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

public class Aro extends ObjetoGrafico{
    private BufferedImage aroGrande, aroChico; //Creacion de las imagenes
    public double aroGrandePosX = 800;
    public static int aroGrandePosY = 217;
    public int aroChicoPosX;
    public static int aAroChicoPosY = 217;

    public Aro(String filename) {
        super(filename);
        try{
            this.aroGrande = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
            this.aroChico = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    public void setPosition(double x, int y){
        this.aroGrandePosX = x;
        this.aroGrandePosY = y;
    }

    public void setAroGrandePosX(double posX){
        this.aroGrandePosX = posX;
    }

    public double getAroGrandePosX (){
        return aroGrandePosX;
    }

    public int getAroGrandePosY(){
        return aroGrandePosY;
    }

    public static void mezclarAros(int[] aros) {
        Random rand = new Random();
        for (int i = 0; i < aros.length; i++) {
            int randomIndexToSwap = rand.nextInt(aros.length);
            int temp = aros[randomIndexToSwap];
            aros[randomIndexToSwap] = aros[i];
            aros[i] = temp;
        }
    }
    
    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        g.drawImage(aroGrande, (int)getAroGrandePosX(), getAroGrandePosY(), null);
    }
}