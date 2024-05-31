package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class MonoAzul extends ObjetoGrafico{
    private double idx = 0;
    private double velocityY = 20; 
    private double gravity = 0.5;
    private int indiceImagenActualMonoAzul = 0;
    private boolean onGround = true;
    private boolean isJumping = false;
    BufferedImage imagen;
    private ArrayList<BufferedImage> imageMonoAzul = new ArrayList<>();

    public MonoAzul(String filename) {
        super(filename);
        try{
            if(imageMonoAzul.isEmpty()){
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
                imageMonoAzul.add(imagen);
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel2/monoPolenta2.png")));
                imageMonoAzul.add(imagen);
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel2/monoPolenta3.png")));
                imageMonoAzul.add(imagen);
            }
        } catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        double posX = getX();
        int posY = (int) getY();
        if (!imageMonoAzul.isEmpty()){
            BufferedImage imagenActualMonoAzul = imageMonoAzul.get(indiceImagenActualMonoAzul);
            if (imagenActualMonoAzul != null){
                g.drawImage(imagenActualMonoAzul, (int) Math.round(posX), (int)posY, null);
            }
        }
    }

    // Parte que nose si anda de blackbox
    public void startJump() {
        if (onGround) {
            velocityY = -10;
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
        int posY = (int) this.getY();
        long currentTime = System.currentTimeMillis();
        if (isJumping) { // Limitar el tiempo de salto a 200ms
            endJump();
            isJumping = false;
        }
        velocityY += gravity;
        setY(posY + velocityY);
        if (posY > 240.0) { // Cambiar la posición de suelo a 240
            setY(240);
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
}
