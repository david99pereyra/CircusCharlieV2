package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;

import jgame.gradle.ObjetoGrafico;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class MonoMarron extends ObjetoGrafico {
    public double posX = 800, idx = 0;
    public final int posY = 220;
    BufferedImage imagen1, imagen2, imagen3;
    private ArrayList<BufferedImage> imageMonoMarron = new ArrayList<>();
    private int indiceImagenActualMonoMarron = 0;
    public MonoMarron(String filename) {
        super(filename);
        try{
            imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
            imageMonoMarron.add(imagen1);
            imagen2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel2/mono2.png")));
            imageMonoMarron.add(imagen2);
            imagen3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel2/mono3.png")));
            imageMonoMarron.add(imagen3);
        } catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        if (!imageMonoMarron.isEmpty()){
            BufferedImage imagenActualMonoMarron = imageMonoMarron.get(indiceImagenActualMonoMarron);
            if (imagenActualMonoMarron != null){
                g.drawImage(imagenActualMonoMarron, (int) Math.round(posX), posY, null);
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
            indiceImagenActualMonoMarron = (indiceImagenActualMonoMarron + 1) % imageMonoMarron.size();
        }
    }

    public void update(double delta){
        idx += 0.04;
        indiceImagenActualMonoMarron = ((int)idx) % imageMonoMarron.size();
    }

    public void setPosition(double x){
        this.posX = x;
    }
    public double getX(){
        return this.posX;
    }
}
