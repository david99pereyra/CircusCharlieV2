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
    private double idx = 0;
    private int indiceImagenActualAroGrande = 0;
    private int indiceImagenActualAroChico = 0;
    private boolean verificarTamaño; //True es para saber si es el AroGrande y false si es Aro Chico
    private ArrayList<BufferedImage> imageAroGrandeIzquierdo = new ArrayList<>();
    private ArrayList<BufferedImage> imageAroGrandeDerecho = new ArrayList<>();
    private ArrayList<BufferedImage> imageAroChicoIzquierdo = new ArrayList<>();
    private ArrayList<BufferedImage> imageAroChicoDerecho = new ArrayList<>();
    
    public Aro(String filename, boolean verificarTam){
        super(filename);
        this.verificarTamaño = verificarTam;
        try{
            if (imagen1 == null && imagen2 == null){
                imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Izquierda.png")));
                imageAroGrandeIzquierdo.add(imagen1);
                imagen2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego2Izquierda.png")));
                imageAroGrandeIzquierdo.add(imagen2);
            }
            if (imagen3 == null && imagen4 == null){
                imagen3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Derecha.png")));
                imageAroGrandeDerecho.add(imagen3);
                imagen4 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego2Derecha.png")));
                imageAroGrandeDerecho.add(imagen4);
            }
            if (imagen5 == null && imagen6 == null){
                imagen5 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1Izquierdo.png")));
                imageAroChicoIzquierdo.add(imagen5);
                imagen6 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico2Izquierdo.png")));
                imageAroChicoIzquierdo.add(imagen6);
            }
            if (imagen7 == null && imagen8  == null){
                imagen7 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1Derecho.png")));
                imageAroChicoDerecho.add(imagen7);
                imagen8 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico2Derecho.png")));
                imageAroChicoDerecho.add(imagen8);
            }
        } catch (IOException e){
            throw new RuntimeException("Error al cargar la imagen del caldero", e);
        }
    }

    // Dibujar el aro en la posición especificada
    public void display(Graphics2D g) {
        double posX = getX();
        int posY = (int) getY();
        if (!imageAroGrandeIzquierdo.isEmpty() && this.verificarTamaño == true){
            BufferedImage imagenActualAroGrande = imageAroGrandeIzquierdo.get(indiceImagenActualAroGrande);
            if (imagenActualAroGrande != null){
                g.drawImage(imagenActualAroGrande, (int) Math.round(posX), posY, null);
            }
        }
        if (!imageAroChicoIzquierdo.isEmpty() && this.verificarTamaño == false){
            BufferedImage imagenActualAroChico = imageAroChicoIzquierdo.get(indiceImagenActualAroChico);
            if (imagenActualAroChico != null){
                g.drawImage(imagenActualAroChico, (int) Math.round(posX), posY, null);
            }
        }
    }
    public void display1(Graphics2D g) {
        double posX = getX();
        int posY = (int) getY();
        if (!imageAroGrandeDerecho.isEmpty() && this.verificarTamaño == true){
            BufferedImage imagenActualAroGrandeDerecho = imageAroGrandeDerecho.get(indiceImagenActualAroGrande);
            if (imagenActualAroGrandeDerecho != null){
                g.drawImage(imagenActualAroGrandeDerecho, (int) Math.round(posX+40), posY, null);
            }
        }
        if (!imageAroChicoDerecho.isEmpty() && this.verificarTamaño == false){
            BufferedImage imagenActualAroChicoDerecho = imageAroChicoDerecho.get(indiceImagenActualAroChico);
            if (imagenActualAroChicoDerecho != null){
                g.drawImage(imagenActualAroChicoDerecho, (int) Math.round(posX+40), posY, null);
            }
        }
    }

    public void update(double delta){
        idx += 0.10;
        if(this.verificarTamaño == true){
            indiceImagenActualAroGrande = ((int)idx) % imageAroGrandeIzquierdo.size();
        }
        if(this.verificarTamaño == false){
            indiceImagenActualAroChico = ((int)idx) % imageAroChicoIzquierdo.size();
        }
    }

    public boolean getVerificarTamaño(){
        return this.verificarTamaño;
    }
}