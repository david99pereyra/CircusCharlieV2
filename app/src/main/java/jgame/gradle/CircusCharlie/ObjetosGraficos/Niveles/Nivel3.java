package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;
import jgame.gradle.CircusCharlie.*;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.DetectorColiciones;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.Pelota;

import java.util.*;
import java.awt.Graphics2D;

public class Nivel3 extends Nivel{
    private static ArrayList<Pelota> listaDePelotas = new ArrayList<>();
    public static Charlie charlie;
    private boolean accionEjecutar = false;
    private static boolean llegoAMeta = false;
    Date dInit = new Date();
    Date dReloj;
    Date dAhora;

    private Timer temporizador = new Timer();

    public Nivel3(CircusCharlie circusCharlie){
        super(circusCharlie);
        Mundo m = Mundo.getInstance();
        try {
            FXPlayer.init();
            FXPlayer.volume = FXPlayer.Volume.LOW;
            //FXPlayer.EVENTO1.loop(); 
            charlie = new Charlie("imagenes/JuegoCircusCharlie/ImagenNivel2/charlieSoga1.png");
            charlie.setPISO(430);
            charlie.setPosition(174, charlie.getPISO());
            charlie.quieto();
            fondo = new Fondo("imagenes/JuegoCircusCharlie/ImagenNivel3/FONDO_Nivel3.png");
            cam = new Camara(0, 0);
            cam.setRegionVisible(circusCharlie.getWidth(), 480);
            m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());
            CircusCharlie.setCharlie(charlie);
            CircusCharlie.setCamara(cam);
            CircusCharlie.setFondo(fondo);
            //Crear las pelotas
            this.crearPelota();
        } catch (Exception e) {
            System.out.println("ERROR 2");
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
        primerPelota.setPosition(posXPixel, 471);
        listaDePelotas.add(primerPelota);
        for (int i = 0; i < 14; i++){
            // Generar un número aleatorio entre 2 y 5
            cantPelotasContinuas = 2 + (int)(Math.random() * ((5 - 2) + 1)); 
            for (int j = 0; j < cantPelotasContinuas; j ++){
                // Generar un número aleatorio entre 350 y 600 para los pixeles
                numeroAleatorioPosX = 350 + (int)(Math.random() * ((600 - 350) + 1));
                posXPixel += numeroAleatorioPosX;
                Pelota pelotita = new Pelota(imagenPelota, false);
                pelotita.setPosition(posXPixel, 471);
                listaDePelotas.add(pelotita);
            }
            numeroAleatorioPosX = 250 + (int)(Math.random() * ((400 - 250) + 1));
            posXPixel += numeroAleatorioPosX;
            Pelota pelotita = new Pelota(imagenPelota, false);
            pelotita.setPosition(posXPixel, 471);
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
                charlie.sumarBonusScore();
                temporizador.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (!accionEjecutar) {
                            System.out.println("esperando 2...");
                            CircusCharlie.setNivel(CircusCharlie.getNivel()+1);
                            Nivel1.setCharlie(charlie);
                            CircusCharlie.inicioNivel(false);
                            CircusCharlie.changeState(new Nivel1(circusCharlie));
                            accionEjecutar = true;
                        }
                    }
                }, 4000);
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
            if (!pelotita.getEstaMontado() && pelotita.getChocarContraotros()) {
                pelotita.update(delta);
                pelotita.setPosition(pelotita.getX() - 0.9, pelotita.getY());
            }
            
        }
        // Detectar si Charlie está en la pelota y actualizar estado
        boolean charlieEnPelota = false;
        for (Pelota pelotita : listaDePelotas) {
            if (DetectorColiciones.detectarCharlieParadoSobrePelota(pelotita, charlie)) {
                charlie.setEnLaPelota(true);
                pelotita.setEstaMontado(true);
                charlie.setPISO(407);
                charlieEnPelota = true;
            } else {
                pelotita.setEstaMontado(false);
            }
        }
        charlie.setEnLaPelota(charlieEnPelota);
    
        if (!charlie.getEnLaPelota()) {
            charlie.setVelocidadCaida(charlie.getGravedad() * delta);
            if (charlie.getY() >= 550) {
                reiniciarJuegoXColisiones(charlie.getX(), charlie);
                charlie.setVelocidadCaida(0);
                Pelota nuevaPelota = new Pelota("imagenes/JuegoCircusCharlie/ImagenNivel3/Pelota1.png", true);
                nuevaPelota.setPosition(charlie.getX(), 471);
                listaDePelotas.add(nuevaPelota);
            }
        } else {
            charlie.setVelocidadCaida(0); // Resetear la velocidad de caída si está en una pelota
        }
        for (int i = 0; i < listaDePelotas.size(); i++){
            Pelota pelotita1 = listaDePelotas.get(i);
            for (int j = i + 1; j < listaDePelotas.size(); j++){
                Pelota pelotita2 = listaDePelotas.get(j);
                if(DetectorColiciones.detectarEntrePelotas(pelotita1, pelotita2)){
                        pelotita1.leftMax(10);  // Pelotita1 a la izquierda
                        pelotita2.leftMax(16); // Pelotita2 a la derecha
                }
            }
        }
        // Detectar colisiones entre pelotas
        for (int i = 0; i < listaDePelotas.size(); i++) {
            Pelota pelotita1 = listaDePelotas.get(i);
            for (int j = i + 1; j < listaDePelotas.size(); j++) {
                Pelota pelotita2 = listaDePelotas.get(j);
                if (DetectorColiciones.detectarEntrePelotas(pelotita1, pelotita2)) {
                    pelotita1.setChocarContraotros(true);
                    pelotita2.setChocarContraotros(true);
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

    public static Pelota getPelotaEnLaQueEstaParadoCharlie(Charlie charlie) {
        for (Pelota pelotita : listaDePelotas) {
            if (pelotita.isCharlieOnTop(charlie)) {
                return pelotita;
            }
        }
        return null; // Si no está parado en ninguna pelota
    }

    public boolean colisiono() {
        return colisiono;
    }
}
