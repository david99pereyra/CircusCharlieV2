package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class MonoAzul extends ObjetoGrafico{
    public double posX = 800, idx = 0;
    public final int posY = 220;
    protected double velocityY = 0.0;
    private boolean onGround = false;
    
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
                g.drawImage(imagenActualMonoAzul, (int) Math.round(posX), posY, null);
            }
        }
    }

    public void jump() {
		if (onGround) {
			velocityY = -12.0;
			onGround = false;
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

    public void setPosition(double x){
        this.posX = x;
    }
}
