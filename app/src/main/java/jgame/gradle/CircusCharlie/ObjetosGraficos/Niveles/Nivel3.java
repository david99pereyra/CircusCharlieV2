package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;
import jgame.gradle.CircusCharlie.Charlie;
import jgame.gradle.CircusCharlie.FXPlayer;
import jgame.gradle.CircusCharlie.Fondo;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.DetectorColiciones;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.MonoMarron;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.Pelota;

import java.util.ArrayList;
import java.awt.Graphics2D;

public class Nivel3 {
    private ArrayList<Pelota> listaDePelotas = new ArrayList<>();
    private static boolean llegoAMeta = false;

    public Nivel3(Charlie charlie, Fondo fondo){
        try {
            FXPlayer.init();
            FXPlayer.volume = FXPlayer.Volume.LOW;
            //FXPlayer.EVENTO1.loop(); 
            
            //Crear los Monos
            this.crearPelota();
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static boolean llegoAMeta(){
        return llegoAMeta;
    }

    private void crearPelota(){
        String imagenPelota = "imagenes/JuegoCircusCharlie/ImagenNivel3/Pelota1.png";
        int numeroAleatorioPosX, cantMonosNormalesContinuos;
        int posXPixel = 174;
        Pelota primerPelota = new Pelota(imagenPelota);
        primerPelota.setPosition(185, 430);
        listaDePelotas.add(primerPelota);
    }

    public void dibujar(Graphics2D g, Charlie charlie){
        for (Pelota pelotita : listaDePelotas){
            pelotita.display(g);
        }
        charlie.display(g);
    }
    
    public void actualizar(double delta, Charlie charlie){
        double posx = charlie.getX()+(charlie.getWidth()/2);
        double posy = charlie.getY()+charlie.getHeight();
        if(posx > 6464 && charlie.getY() < 417){
            charlie.setPISO(380);
            
            if(charlie.getY() >= charlie.getPISO()){
                llegoAMeta = true;
            }
            if(posx < 6525 && posy >= charlie.getPISO()){
                charlie.setX(charlie.getX()+1);
            }else if(posx > 6525 && posy >= charlie.getPISO()){
                charlie.setX(charlie.getX()-1);
            }
        }
        else if(charlie.getX() < 6464 || charlie.getX()> 6586){
            // charlie.setPISO(430);
        }
        
        for(Pelota pelotita : listaDePelotas){
            if(DetectorColiciones.detectarCharlieParteSuperiorPelota(pelotita, charlie)){
                System.out.println("Estas montado");
                charlie.setPISO(300);
                charlie.setPosition(pelotita.getX(), charlie.getPISO());
            }
        }
    }
}
