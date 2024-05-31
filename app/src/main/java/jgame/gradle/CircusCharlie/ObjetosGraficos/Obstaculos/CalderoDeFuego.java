package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.*;
import java.util.*;
public class CalderoDeFuego extends ObjetoGrafico {
    private double idx;
    BufferedImage imagen;
    private ArrayList<BufferedImage> imagenes = new ArrayList<>();
    private int indiceImagenActual = 0;
    public CalderoDeFuego(String filename) {
        super(filename);
        try {
            if(imagenes.isEmpty()){
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
                imagenes.add(imagen);
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/fuego2.png")));
                imagenes.add(imagen);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        double posX = getX();
        int posY = (int) getY();
        if (!imagenes.isEmpty()){
            BufferedImage imagenActualMonoMarron = imagenes.get(indiceImagenActual);
            if (imagenActualMonoMarron != null){
                g.drawImage(imagenActualMonoMarron, (int) Math.round(posX), posY, null);
            }
        }
    }

    public void update(double delta){
        idx += 0.165;
        indiceImagenActual = ((int)idx) % imagenes.size();
    }
}