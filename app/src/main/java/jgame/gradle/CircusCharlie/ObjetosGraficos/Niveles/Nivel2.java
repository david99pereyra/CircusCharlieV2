package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;
import jgame.gradle.CircusCharlie.Charlie;
import jgame.gradle.CircusCharlie.CircusCharlie;
import jgame.gradle.CircusCharlie.FXPlayer;
import jgame.gradle.CircusCharlie.Fondo;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.MonoMarron;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.DetectorColiciones;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.MonoAzul;

import java.util.*;
import java.awt.Graphics2D;

public class Nivel2 extends Nivel{
    private ArrayList<MonoMarron> listaDeMonosMarron = new ArrayList<>();
    private ArrayList<MonoAzul> listaDeMonosAzul = new ArrayList<>();
    private static boolean llegoAMeta = false, colisiono = false;
    Date dInit = new Date();
    Date dReloj;
    Date dAhora;

    public Nivel2(Charlie charlie, Fondo fondo){
        try {
            FXPlayer.init();
            FXPlayer.volume = FXPlayer.Volume.LOW;
            //FXPlayer.EVENTO1.loop(); 
            
            //Crear los Monos
            this.crearMonos();
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static boolean llegoAMeta(){
        return llegoAMeta;
    }

    public void actualizar(double delta, Charlie charlie){
        // Posicionamiento en el podio
        double posx = charlie.getX() + (charlie.getWidth()/2);
        double posy = charlie.getY() + charlie.getHeight();
        if(posx > 5550 && charlie.getY() < 174){
            charlie.setPISO(150);
            if(charlie.getY() >= charlie.getPISO()){
                llegoAMeta = true;
            }
            if(posx < 5650 && posy >= charlie.getPISO()){
                charlie.setX(charlie.getX()+1);
            }else if(posx>5650 && posy >= charlie.getPISO()){
                charlie.setX(charlie.getX()-1);
            }
        }
        else if(charlie.getX()<5550 || charlie.getX()>5743){
            charlie.setPISO(220);
        }
        // Movimiento de los pinches monos y detectar colisiones de los entre charlie y monos
        if(!colisiono){          
            for (MonoAzul mA : listaDeMonosAzul){
                mA.update(delta);
                mA.setPosition(mA.getX() - 2.5);
                if(DetectorColiciones.detectarMonoAzul(mA, charlie)){
                    reiniciarJuegoXColisiones(charlie.getX(),charlie);
                }
            }
            for (MonoMarron mM : listaDeMonosMarron){
                if(!mM.getIsStopped()){
                    mM.update(delta);
                    mM.setPosition(mM.getX() - 1);
                }
                if(DetectorColiciones.detectarMonoNormal(mM, charlie)){
                    reiniciarJuegoXColisiones(charlie.getX(),charlie);
                }
            }
        }

        // Detectar colision entre monos
        for(MonoMarron mM : listaDeMonosMarron){
            for(MonoAzul mA: listaDeMonosAzul){
                if(DetectorColiciones.detectarEntreMonos(mM, mA)){
                    mA.saltoMonoAZul();
                    mM.detenerMono();
                }
            }
        }
        // dance para cuando Charlie llego a la meta
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

    public void dibujar(Graphics2D g, Charlie charlie){
        for (MonoMarron monitoMarron: listaDeMonosMarron){
            monitoMarron.display(g);
        }
        for (MonoAzul monitoAzul: listaDeMonosAzul){
            monitoAzul.display(g);
        }
        charlie.display(g);
    }

public void crearMonos(){
        String imagenMonoMarron = "imagenes/JuegoCircusCharlie/ImagenNivel2/Mono1.png";
        String imagenMonoAzul = "imagenes/JuegoCircusCharlie/ImagenNivel2/monoPolenta1.png";
        int numeroAleatorioPosX, cantMonosNormalesContinuos;
        int posXPixel = 850;

        MonoMarron primerMonito = new MonoMarron(imagenMonoMarron);
        primerMonito.setPosition(posXPixel, 220);
        listaDeMonosMarron.add(primerMonito);
        for (int i = 0; i < 14; i++){
            // Generar un número aleatorio entre 2 y 5
            cantMonosNormalesContinuos = 2 + (int)(Math.random() * ((5 - 2) + 1)); 
            for (int j = 0; j < cantMonosNormalesContinuos; j ++){
                // Generar un número aleatorio entre 350 y 600 para los pixeles
                numeroAleatorioPosX = 350 + (int)(Math.random() * ((600 - 350) + 1));
                posXPixel += numeroAleatorioPosX;
                MonoMarron monitoMarron = new MonoMarron(imagenMonoMarron);
                monitoMarron.setPosition(posXPixel);
                listaDeMonosMarron.add(monitoMarron);
            }
            numeroAleatorioPosX = 250 + (int)(Math.random() * ((400 - 250) + 1));
            posXPixel += numeroAleatorioPosX;
            MonoAzul monitoAzul = new MonoAzul(imagenMonoAzul);
            monitoAzul.setPosition(posXPixel);
            listaDeMonosAzul.add(monitoAzul);
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

    @Override
    public void dibujar(CircusCharlie charlie) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dibujar'");
    }

    @Override
    public void actualizar(CircusCharlie charlie) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }
}
