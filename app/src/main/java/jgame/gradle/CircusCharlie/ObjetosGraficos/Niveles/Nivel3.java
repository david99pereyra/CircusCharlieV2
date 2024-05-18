package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;
import jgame.gradle.CircusCharlie.Charlie;
import jgame.gradle.CircusCharlie.CircusCharlie;
import jgame.gradle.CircusCharlie.FXPlayer;
import jgame.gradle.CircusCharlie.Fondo;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.DetectorColiciones;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.Pelota;

import java.util.*;
import java.awt.Graphics2D;

public class Nivel3 extends Nivel{
    private ArrayList<Pelota> listaDePelotas = new ArrayList<>();
    private static boolean llegoAMeta = false;
    private boolean colisiono = false;
    public static Charlie charlie;
    Date dInit = new Date();
    Date dReloj;
    Date dAhora;

    public Nivel3(CircusCharlie circusCharlie){
        super(circusCharlie);
        try {
            FXPlayer.init();
            FXPlayer.volume = FXPlayer.Volume.LOW;
            //FXPlayer.EVENTO1.loop(); 
            charlie.setPISO(430);
            charlie.setPosition(174, charlie.getPISO());
            //Crear las pelotas
            this.crearPelota();
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static boolean llegoAMeta(){
        return llegoAMeta;
    }

    public static void setCharlie(Charlie charlie){
        Nivel3.charlie = charlie;
    }

    private void crearPelota(){
        String imagenPelota = "imagenes/JuegoCircusCharlie/ImagenNivel3/Pelota1.png";
        int numeroAleatorioPosX, cantPelotasContinuas;
        int posXPixel = 185;

        Pelota primerPelota = new Pelota(imagenPelota, true);
        primerPelota.setPosition(posXPixel);
        listaDePelotas.add(primerPelota);
        for (int i = 0; i < 14; i++){
            // Generar un número aleatorio entre 2 y 5
            cantPelotasContinuas = 2 + (int)(Math.random() * ((5 - 2) + 1)); 
            for (int j = 0; j < cantPelotasContinuas; j ++){
                // Generar un número aleatorio entre 350 y 600 para los pixeles
                numeroAleatorioPosX = 350 + (int)(Math.random() * ((600 - 350) + 1));
                posXPixel += numeroAleatorioPosX;
                Pelota pelotita = new Pelota(imagenPelota, false);
                pelotita.setPosition(posXPixel);
                listaDePelotas.add(pelotita);
            }
            numeroAleatorioPosX = 250 + (int)(Math.random() * ((400 - 250) + 1));
            posXPixel += numeroAleatorioPosX;
            Pelota pelotita = new Pelota(imagenPelota, false);
            pelotita.setPosition(posXPixel);
            listaDePelotas.add(pelotita);
        } 
    }

    public void gameDraw(Graphics2D g){
        for (Pelota pelotita : listaDePelotas){
            pelotita.display(g);
        }
        charlie.display(g);
    }
    
    public void gameUpdate(double delta){
        double posx = charlie.getX()+(charlie.getWidth()/2);
        double posy = charlie.getY()+charlie.getHeight();
        if(posx > 6464 && charlie.getY() < 420){
            charlie.setPISO(407);
            
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
            charlie.setPISO(430);
        }
        for (Pelota pelotita : listaDePelotas) {
            if (pelotita.getEstaMontado()) {
                pelotita.setPosition(pelotita.getPosX());
            } else {
                pelotita.update(delta);
                pelotita.setPosition(pelotita.getPosX() - 0.9);
            }
        }
        
        // Detectar si Charlie está en la pelota
        for (Pelota pelotita : listaDePelotas) {
            if (DetectorColiciones.detectarCharlieParadoSobrePelota(pelotita, charlie)) {
                charlie.setEnLaPelota(true);
                pelotita.setEstaMontado(true);
                System.out.println("Charlie está montado en una pelota");
                charlie.setPISO(407);
            }
        }
        if(!charlie.getEnLaPelota()){
            charlie.setVelocidadCaida(charlie.getGravedad() * delta);
            charlie.setPosition(charlie.getX(), charlie.getY() + charlie.getVelocidadCaida() * delta);
            if (charlie.getY() >= charlie.getPISO()) {
                charlie.setPosition(charlie.getX(), charlie.getPISO());
                charlie.setVelocidadCaida(0);
                reiniciarJuegoXColisiones(charlie.getX(), charlie);            
            }
        }else{
            charlie.setVelocidadCaida(0); // Resetear la velocidad de caída si está en una pelota
        } 
    
        // Ejemplo de detectar colisiones entre pelotas (si es necesario)
        for (int i = 0; i < listaDePelotas.size(); i++) {
            Pelota pelotita1 = listaDePelotas.get(i);
            pelotita1.update(delta);
            for (int j = i + 1; j < listaDePelotas.size(); j++) {
                Pelota pelotita2 = listaDePelotas.get(j);
                if (DetectorColiciones.detectarEntrePelotas(pelotita1, pelotita2)) {
                    // Pelotas colisionaron, ajustar la velocidad
                    pelotita1.leftMax(14);
                    pelotita2.leftMax(12);
                }
            }
        }
        if(llegoAMeta()){
            if (dReloj == null){
                dReloj = new Date();
            }
            dAhora= new Date();
            long diffSeconds = 0;
            long dateDiff = dAhora.getTime() - dReloj.getTime();
            diffSeconds = dateDiff / 1000 % 60;
            charlie.updateLlegadaMeta(delta);
        }
    }

    public void reiniciarJuegoXColisiones(double x1, Charlie charlie){
        // Busca el checkpoint más cercano a la posición x
        int[] checkpointsEjeX = {201, 990, 1814, 2654, 3451, 4259, 5066, 5869, 6668, 7433};
        int pos = 0, i;
        for (i = 1; i < checkpointsEjeX.length; i++) {
            if (checkpointsEjeX[i] < x1) {
                pos = i - 1;
            }
        }
        // Reinicia el juego en el checkpoint más cercano
        int newX = checkpointsEjeX[pos]; 
        reiniciarJuego(newX, charlie);
    }

    private void reiniciarJuego(double x, Charlie charlie) {
        charlie.setPISO(412);
        charlie.setPosition(x + 31,charlie.getPISO());
        llegoAMeta = false;
        colisiono = false;
        FXPlayer.DERROTA.stop();
        //FXPlayer.EVENTO1.loop();
        charlie.setImagen("imagenes/JuegoCircusCharlie/Generales/charlie.png");
    }

    public Pelota getPelotaEnLaQueEstaParadoCharlie(Charlie charlie) {
        for (Pelota pelotita : listaDePelotas) {
            if (pelotita.isCharlieOnTop(charlie)) {
                return pelotita;
            }
        }
        return null; // Si no está parado en ninguna pelota
    }
}
