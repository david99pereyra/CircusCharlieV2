package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import jgame.gradle.CircusCharlie.ObjetoGrafico;


public class Aro extends ObjetoGrafico{

    BufferedImage imagen1, imagen2, imagen3, imagen4, imagen5, imagen6,imagen7, imagen8;
    public double posX = 800, idx = 0;
    public final int posY = 217;
    private ArrayList<BufferedImage> imageAroGrande = new ArrayList<>();
    private ArrayList<BufferedImage> imageAroGrandeDerecho = new ArrayList<>();
    private ArrayList<BufferedImage> imageAroChico = new ArrayList<>();
    private ArrayList<BufferedImage> imageAroChicoDerecho = new ArrayList<>();
    private int indiceImagenActualAroGrande = 0, indiceImagenActualAroChico = 0;
    public boolean verificarTamaño; //True es para saber si es el AroGrande y false si es Aro Chico
    int cont= 0;
    
    public Aro(String filename, boolean verificarTam){
        super(filename);
        this.verificarTamaño = verificarTam;
        try{
            imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Izquierda.png")));
            imageAroGrande.add(imagen1);
            imagen2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego2Izquierda.png")));
            imageAroGrande.add(imagen2);

            imagen3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Derecha.png")));
            imageAroGrandeDerecho.add(imagen3);
            imagen4 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego2Derecha.png")));
            imageAroGrandeDerecho.add(imagen4);

            imagen5 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1Izquierdo.png")));
            imageAroChico.add(imagen5);
            imagen6 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico2Izquierdo.png")));
            imageAroChico.add(imagen6);

            imagen7 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1Derecho.png")));
            imageAroChicoDerecho.add(imagen7);

            imagen8 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico2Derecho.png")));
            imageAroChicoDerecho.add(imagen8);
        } catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        if (!imageAroGrande.isEmpty() && verificarTamaño == true){
            BufferedImage imagenActualAroGrande = imageAroGrande.get(indiceImagenActualAroGrande);
            if (imagenActualAroGrande != null){
                g.drawImage(imagenActualAroGrande, (int) Math.round(posX), posY, null);
            }
        }
        if (!imageAroChico.isEmpty() && verificarTamaño == false){
            BufferedImage imagenActualAroChico = imageAroChico.get(indiceImagenActualAroChico);
            if (imagenActualAroChico != null){
                g.drawImage(imagenActualAroChico, (int) Math.round(posX), posY, null);
            }
        }
    }
    public void display1(Graphics2D g) {
        if (!imageAroGrandeDerecho.isEmpty() && verificarTamaño == true){
            BufferedImage imagenActualAroGrandeDerecho = imageAroGrandeDerecho.get(indiceImagenActualAroGrande);
            if (imagenActualAroGrandeDerecho != null){
                g.drawImage(imagenActualAroGrandeDerecho, (int) Math.round(posX+40), posY, null);
            }
        }
        if (!imageAroChicoDerecho.isEmpty() && verificarTamaño == false){
            BufferedImage imagenActualAroChicoDerecho = imageAroChicoDerecho.get(indiceImagenActualAroChico);
            if (imagenActualAroChicoDerecho != null){
                g.drawImage(imagenActualAroChicoDerecho, (int) Math.round(posX+40), posY, null);
            }
        }
    }

    public void SwapImage(){
        while (true) {
            try{
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException("Error al cargar la imagen del caldero", ex);
            }
            indiceImagenActualAroGrande = (indiceImagenActualAroGrande + 1) % imageAroGrande.size();
            indiceImagenActualAroChico = (indiceImagenActualAroChico + 1) % imageAroChico.size();
        }
    }

    public void update(double delta){
        idx += 0.04;
        if(this.verificarTamaño == true){
            indiceImagenActualAroGrande = ((int)idx) % imageAroGrande.size();
        }
        if(this.verificarTamaño == false){
            indiceImagenActualAroChico = ((int)idx) % imageAroChico.size();
        }
    }

    public void setPosition(double x, int y){
        this.posX = x;
    }

    public double getAroPosX (){
        return posX;
    }

    public int getAroPosY(){
        return posY;
    }

    public void setAroGrandePosX(double posX){
        this.posX = posX;
    }
}