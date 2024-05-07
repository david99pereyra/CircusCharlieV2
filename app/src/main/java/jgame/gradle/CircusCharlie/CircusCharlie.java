/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jgame.gradle.CircusCharlie;

import com.entropyinteractive.*;  //jgame
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.Aro;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.CalderoDeFuego;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.DetectorColiciones;

import java.awt.*;
import java.awt.event.*; //eventos
import java.awt.image.*;  //imagenes
import javax.imageio.*; //imagenes
import java.awt.geom.*; //Point2d
import java.util.*;
import java.text.*;

public class CircusCharlie extends JGame {
    private boolean level1 = true, level2 = false, level3 =false;
    Date dInit = new Date( ), dAhora;
    SimpleDateFormat ft = new SimpleDateFormat ("mm:ss");
    Camara cam;
    Fondo fondo;
    Charlie charlie, leon;
    //Variables del level 1
    private ArrayList<Aro> listaDeAros = new ArrayList<>();
    private ArrayList<CalderoDeFuego> listaDeCalderos = new ArrayList<>();
    //Variables del level 2

    //Variables del level 3

    public static void main(String[] args) {
        CircusCharlie game = new CircusCharlie();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public CircusCharlie() {
        // call game constructor
        super("AppCamaracharlie ", 1500, 600);
    }

    public void gameStartup() {
        Log.info(getClass().getSimpleName(), "Starting up game");
        if(level1){} //Aca va lo del nivel 1
        if(level2){} //Aca va lo del nivel 2
        if(level3){} //Aca va lo del nivel 3
        try{        
            FXPlayer.init();
            FXPlayer.volume = FXPlayer.Volume.LOW;
        
            Mundo m=Mundo.getInstance();

            charlie=new Charlie("imagenes/charlie.png");
            charlie.setPISO(412);
            charlie.setPosition(172,charlie.getPISO());
            leon=new Charlie("imagenes/leon.png");
            leon.setPISO(475);
            leon.setPosition(143, leon.getPISO());

            cam =new Camara(0,0);

            cam.setRegionVisible(getWidth(),480);

            //fondo=new Fondo("imagenes/background.png");
            fondo=new Fondo("imagenes/FONDO.png");
            m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());
            charlie.quieto();

            //Crear los aros
            crearAros();
            //Crear los calderos
            crearCalderos();

            FXPlayer.EVENTO1.loop();
        }catch(Exception ex){
            System.out.println("ERROR en gameStartup");
            ex.printStackTrace();
        }
    }
    
    public void gameUpdate(double delta) {
        if(level1){} //Aca va lo del nivel 1
        if(level2){} //Aca va lo del nivel 2
        if(level3){} //Aca va lo del nivel 3
        Keyboard keyboard = getKeyboard();
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)){
            if(leon.getX() > 10){
                charlie.left();
                leon.left();
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)){
            if(leon.getX()<(fondo.getWidth()-116)){
                charlie.right();
                leon.right();
            }
        }

        // check the list of key events for a pressed escape key
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getID() == KeyEvent.KEY_RELEASED)){
                charlie.quieto();
                leon.quieto();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                charlie.jump();
                leon.jump();
                FXPlayer.FX00.play();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {

                FXPlayer.EVENTO1.stop();
                stop();
            }
        }
        leon.update(delta);
        charlie.update(delta);
        for (Aro aro : listaDeAros) {
            aro.setAroGrandePosX(aro.getAroGrandePosX() - 0.6);
        }
        // aroTimer.scheduleAtFixedRate(new GenerarArosTask(), 0, 5000); // Genera aros cada 5 segundos

        for (Aro aro : listaDeAros) {
            if (DetectorColiciones.detectarAroGrande(aro, charlie)){
                System.out.println("COLISIONASTE ESTUPIDO, TENE CUIDADO");
            }
        }

        for(CalderoDeFuego calderito : listaDeCalderos){
            if (DetectorColiciones.detectarCalderoDeFuego(calderito, charlie)){
                System.out.println("COLISIONASTE, TENE CUIDADO");
            }
        }
        //charlie.applyForce(gravedad);
        cam.seguirPersonaje(charlie); ///la camara sigue al Personaje
        cam.seguirPersonaje(leon); 
    }

    public void gameDraw(Graphics2D g) {
        if(level1){} //Aca va lo del nivel 1
        if(level2){} //Aca va lo del nivel 2
        if(level3){} //Aca va lo del nivel 3
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Mundo m=Mundo.getInstance();

        g.translate(cam.getX(),cam.getY());

        fondo.display(g);
        m.display(g);
        leon.display(g);
        charlie.display(g);
        //Dibujar aros
        for (Aro aro : listaDeAros) {

            aro.display(g);
        }
        //Dibujar aros
        for (CalderoDeFuego calderito: listaDeCalderos) {
            calderito.display(g);
        }

        g.translate(-cam.getX(),-cam.getY());

        g.setColor(Color.red);
        
        g.drawString("Tecla ESC = Fin del Juego ",490,20);
    }
    //Funcion para crear aros
    public void crearAros(){
        String imagenAroGrande = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1.png";
        String imagenAroChico = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1.png";
        int posXPixel = 800, count = 1; //count hasta 30 y count2 que controla con numerorandom
        Random random = new Random();

        while(posXPixel <= 8272 ){
            int numeroRandom = random.nextInt(4) + 3;
            int count2 = 1; // Inicio y reinicio count2 en cada iteración
            while (count2 <= numeroRandom){
                Aro aro = new Aro(imagenAroGrande);
                aro.setPosition(posXPixel, 217);
                listaDeAros.add(aro);
                posXPixel += 450;
                count2++;
            }
            Aro aro = new Aro(imagenAroGrande);
            aro.setPosition(posXPixel, 217);
            listaDeAros.add(aro);
            posXPixel += 300;
        }
    }
    //Funcion para crear calderos
    public void crearCalderos(){
        String imagen = "imagenes/JuegoCircusCharlie/ImagenNivel1/fuego1.png";
        int[] posicionesX = {1550, 2390, 3180, 3990, 4795, 5600, 6400, 7160, 7970};
        int posY = 435;
        for (int posX : posicionesX) {
            CalderoDeFuego caldero = new CalderoDeFuego(imagen);
            caldero.setPosition(posX, posY);
            listaDeCalderos.add(caldero);
        }
    } 

    public void gameShutdown() {
       //Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}