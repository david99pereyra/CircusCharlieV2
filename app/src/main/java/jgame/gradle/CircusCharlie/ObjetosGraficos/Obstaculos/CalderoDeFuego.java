package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import java.util.ArrayList;


public class CalderoDeFuego extends ObjetoGrafico {
    private double posX, idx;
    private int posY;
    private ArrayList<BufferedImage> imagenes = new ArrayList<>();
    private int indiceImagenActual = 0;
    
    public CalderoDeFuego(String filename) {
        super(filename);
        try {
            BufferedImage imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/fuego1.png")));
            imagenes.add(imagen1);
            BufferedImage imagen2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/fuego2.png")));
            imagenes.add(imagen2);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    public void setPosition(double x, int y) {
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
        if (!imagenes.isEmpty()){
            BufferedImage imagenActualMonoMarron = imagenes.get(indiceImagenActual);
            if (imagenActualMonoMarron != null){
                g.drawImage(imagenActualMonoMarron, (int) Math.round(posX), posY, null);
            }
        }
    }

    public void SwapImage(){
        while (true) {
            try{
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                throw new RuntimeException("Error al cargar la imagen del Mono Marron", ex);
            }
            indiceImagenActual = (indiceImagenActual + 1) % imagenes.size();
        }
    }

    public void update(double delta){
        idx += 0.165;
        indiceImagenActual = ((int)idx) % imagenes.size();
    }
}