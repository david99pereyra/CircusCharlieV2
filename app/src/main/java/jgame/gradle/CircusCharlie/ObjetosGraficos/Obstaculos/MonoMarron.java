package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.ObjetoGrafico;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;


public class MonoMarron extends ObjetoGrafico {
    private double idx = 0;
    private double velocityX;
    private boolean isStopped;
    private final int stopDuration = 1000; // Duración en milisegundos que el mono se detiene
    BufferedImage imagen;
    private ArrayList<BufferedImage> imageMonoMarron = new ArrayList<>();
    private int indiceImagenActualMonoMarron = 0;

    public MonoMarron(String filename) {
        super(filename);
        try{
            if(imageMonoMarron.isEmpty()){
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
                imageMonoMarron.add(imagen);
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel2/mono2.png")));
                imageMonoMarron.add(imagen);
                imagen = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel2/mono3.png")));
                imageMonoMarron.add(imagen);
            }
        } catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        double posX = this.getX();
        int posY = (int) this.getY();
        if (!imageMonoMarron.isEmpty()){
            BufferedImage imagenActualMonoMarron = imageMonoMarron.get(indiceImagenActualMonoMarron);
            if (imagenActualMonoMarron != null){
                g.drawImage(imagenActualMonoMarron, (int) Math.round(posX), posY, null);
            }
        }
    }
    
    public boolean getIsStopped(){
        return isStopped;
    }

    public void update(double delta) {
        double posX = this.getX();
        // Solo actualizar la posición si no está detenido
        if (!isStopped) {
            posX += velocityX * delta;
            idx += 0.04;
            indiceImagenActualMonoMarron = ((int) idx) % imageMonoMarron.size();
        }
    }

    public void detenerMono() {
        if (!isStopped) {
            isStopped = true;
            velocityX = 0; // Detén el movimiento en X al detenerse
            // Programa la reanudación del movimiento después de stopDuration milisegundos
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isStopped = false;
                    velocityX = -2; // Restablece la velocidad en X después de detenerse
                }
            }, stopDuration);
        }
    }
}
