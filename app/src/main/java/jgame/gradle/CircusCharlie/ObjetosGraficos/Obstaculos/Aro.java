package jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import jgame.gradle.CircusCharlie.ObjetoGrafico;


public class Aro extends ObjetoGrafico{
    public double posX = 800, idx = 0;
    public static int posY = 217;
    private ArrayList<BufferedImage> imageAroGrande = new ArrayList<>();
    private ArrayList<BufferedImage> imageAroChico = new ArrayList<>();
    private int indiceImagenActualAroGrande = 0, indiceImagenActualAroChico = 0;
    public boolean verificarTamaño; //True es para saber si es el AroGrande y false si es Aro Chico
    
    public Aro(String filename, boolean verificarTam){
        super(filename);
        this.verificarTamaño = verificarTam;
        try{
            BufferedImage imagen1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1.png")));
            imageAroGrande.add(imagen1);
            BufferedImage imagen2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego2.png")));
            imageAroGrande.add(imagen2);
            BufferedImage imagen3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1.png")));
            imageAroChico.add(imagen3);
            BufferedImage imagen4 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico2.png")));
            imageAroChico.add(imagen4);
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
        this.posY = y;
    }

    public double getAroPosX (){
        return posX;
    }

    public int getAroPosY(){
        return posY;
    }
}