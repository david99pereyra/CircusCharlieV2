package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class MonoAzul extends ObjetoGrafico{
    public double posX = 800, idx = 0, velocityX = 4.0, posY = 220;
    // public int posY = 220;
    private double velocityY = 0, gravity = 0.5;;
    private int indiceImagenActualMonoAzul = 0;
    BufferedImage imagen1;
    private ArrayList<BufferedImage> imageMonoAzul = new ArrayList<>();

    public MonoAzul(String filename) {
        super(filename);
        try{
            imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Derecha.png")));
            imageMonoAzul.add(imagen1);
        } catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        if (!imageMonoAzul.isEmpty()){
            BufferedImage imagenActualMonoAzul = imageMonoAzul.get(indiceImagenActualMonoAzul);
            if (imagenActualMonoAzul != null){
                g.drawImage(imagenActualMonoAzul, (int) Math.round(posX), (int)posY, null);
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
            indiceImagenActualMonoAzul = (indiceImagenActualMonoAzul + 1) % imageMonoAzul.size();
        }
    }

    public void update(double delta){
        idx += 0.04;
        indiceImagenActualMonoAzul = ((int)idx) % imageMonoAzul.size();
    }

    public void saltoMonoAZul(MonoMarron mM) {
        velocityY = -12.0;
        while (velocityY < -6.0) {
            velocityY += gravity;
            posY += velocityY;
            velocityY = - 2.0;
            if(velocityY == -20.0){
                System.out.println("hola");
                while (velocityY != 0) {
                    velocityY -= gravity;
                    posY -= velocityY;
                    velocityY =+ 10.0;
                }
            }
        }
    }

    public void setPosition(double x){
        this.posX = x;
    }
    public double getX(){
        return this.posX;
    }

    public double getPosY(){
        return this.posY;
    }
}
