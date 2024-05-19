package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;
import jgame.gradle.CircusCharlie.Charlie;
import jgame.gradle.CircusCharlie.ObjetoGrafico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Pelota extends ObjetoGrafico{
    private double idx = 0;
    private double velocityX = 5.0;
    private int indiceImagenActualPelota = 0;
    protected int direccionAngulo= 1;
    private boolean fueMontada = false; // Define si fue montado para que la pelota salga disparada para la izquierda
    private boolean estaMontado = false; // Define si esta montado actualmente en la pelota charlie
    private ArrayList<BufferedImage> imagePelota = new ArrayList<>();
    BufferedImage imagen1, imagen2, imagen3, imagen4;
            
    public Pelota(String filename, boolean montado) {
        super(filename);
        this.fueMontada = montado;
        this.estaMontado = montado;
        try{
            if(imagePelota.isEmpty()){
                imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filename)));
                imagePelota.add(imagen1);
                imagen2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel3/Pelota2.png")));
                imagePelota.add(imagen2);
                imagen3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel3/Pelota3.png")));
                imagePelota.add(imagen3);
                imagen4 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel3/Pelota4.png")));
                imagePelota.add(imagen4);
            }
        } catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    public boolean isCharlieOnTop(Charlie charlie) {
        double charliePosX = charlie.getX();
        double charliePosY = charlie.getY() + charlie.getHeight(); // Asumiendo que `getY()` devuelve la parte superior de Charlie
        // Ajusta los valores según el tamaño de la pelota y de Charlie
        return (charliePosX > this.getX() && charliePosX < (this.getX() + getWidth()) && charliePosY >= this.getY() && charliePosY <= (this.getY() + getHeight()));
    }

    public void leftMax(double valor){
        this.setX(getX() - valor);
    }

    public void left() {
        this.setX(getX() - velocityX);
		direccionAngulo =- 1;
	}

	public void right() {
        this.setX(getX() + velocityX);
		direccionAngulo = 1;
	} 

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        double posX = getX();
        int posY = (int) getY();
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

    public void setEstaMontado (boolean montadoActualmente){
        this.estaMontado = montadoActualmente;
    }

    public boolean getEstaMontado (){
        return estaMontado;
    }

    public void setFueMontada (boolean bol){
        this.fueMontada = bol;
    }

    public boolean getFueMontada (){
        return fueMontada;
    }
}
