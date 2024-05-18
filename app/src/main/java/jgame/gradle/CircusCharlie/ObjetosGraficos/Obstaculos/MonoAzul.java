package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class MonoAzul extends ObjetoGrafico{
    private double posX = 800;
    private double idx = 0;
    private double velocityX = 40;
    private double posY = 240;
    private double velocityY = 20; 
    private double gravity = 0.5;
    private int indiceImagenActualMonoAzul = 0;
    private boolean onGround = true, isJumping = false;
    BufferedImage imagen1, imagen2, imagen3;
    private ArrayList<BufferedImage> imageMonoAzul = new ArrayList<>();

    public MonoAzul(String filename) {
        super(filename);
        try{
            imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
            imageMonoAzul.add(imagen1);
            imagen2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel2/monoPolenta2.png")));
            imageMonoAzul.add(imagen1);
            imagen3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel2/monoPolenta3.png")));
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

    // Parte que nose si anda de blackbox
    public void startJump() {
        if (onGround) {
            velocityY = -28;
            onGround = false;
            isJumping = true;
        }
    }

    public void endJump() {
        if (velocityY < -14) {
            velocityY = -14;
        }
    }

    public void update(double delta) {
        long currentTime = System.currentTimeMillis();
        if (isJumping) { // Limitar el tiempo de salto a 200ms
            endJump();
            isJumping = false;
        }
        velocityY += gravity;
        posY += velocityY;

        if (posY > 240.0) { // Cambiar la posición de suelo a 240
            posY = 240.0;
            velocityY = 0.0;
            onGround = true;
        }
        idx += 0.04;
        indiceImagenActualMonoAzul = ((int) idx) % imageMonoAzul.size();
    }

    public void saltoMonoAZul() {
        startJump();
        isJumping = true;
    }

    public void setPosition(double x){
        this.posX = x;
    }

    public double getX(){
        return this.posX;
    }

    public double getY(){
        return this.posY;
    }
}
