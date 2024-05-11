package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Pelota extends ObjetoGrafico{
    public double posX = 174, idx = 0;
    public final int posY = 430;
    BufferedImage imagen1;
    private ArrayList<BufferedImage> imagePelota = new ArrayList<>();
    private int indiceImagenActualPelota = 0;
    
    public Pelota(String filename) {
        super(filename);
        try{
            imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel3/Pelota1.png")));
            imagePelota.add(imagen1);
        } catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        if (!imagePelota.isEmpty()){
            BufferedImage imagenActualMonoPelota = imagePelota.get(indiceImagenActualPelota);
            if (imagenActualMonoPelota != null){
                g.drawImage(imagenActualMonoPelota, (int) Math.round(posX), posY, null);
            }
        }
    }

    public void SwapImage(){
        while (true) {
            try{
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException("Error al cargar la imagen del Mono Marron", ex);
            }
            indiceImagenActualPelota = (indiceImagenActualPelota + 1) % imagePelota.size();
        }
    }

    public void update(double delta){
        idx += 0.04;
        indiceImagenActualPelota = ((int)idx) % imagePelota.size();
    }

    public void setPosition(double x){
        this.posX = x;
    }
}
