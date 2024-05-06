package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.Charlie;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.imageio.ImageIO;

public class CalderoDeFuego extends ObjetoGrafico{
    BufferedImage Caldero;
    private int posX = 100;
    private static int posY = 0;
    private int DISTANCIA_MINIMA = 0;
    private int DISTANCIA_MAXIMA = 60;
    
    public CalderoDeFuego(String filename){
        super(filename);
        this.setPosition(posX, posY);
        try{
            this.Caldero = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    public int getPosX() {
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
        g.drawImage(Caldero, getPosX(), getPosY(), null);
    }

    public static ArrayList<CalderoDeFuego> generarCalderos(){
        Random rand = new Random();
        ArrayList<CalderoDeFuego> calderos = new ArrayList<>();
        //Generar Calderos de fuego
        for (int i = 0; i < 300; i++){
            int x = rand.nextInt(700) + 100;
            CalderoDeFuego calderito = new CalderoDeFuego("imagenes/JuegoCircusCharlie/ImagenNivel1/fuego1.png");
            calderito.setPosition(x, posY);
            calderos.add(calderito);
        }
        return calderos;
    }
}