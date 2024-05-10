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
    int cantPuntos = 0;
    //Variables del level 1
    private ArrayList<Aro> listaDeArosIzquierdo = new ArrayList<>();
    private ArrayList<Aro> listaDeArosDerecho = new ArrayList<>();
    private ArrayList<CalderoDeFuego> listaDeCalderos = new ArrayList<>();

    //Variables del level 2
    Charlie charlie, leon;
    //Variables del level 3

    public static void main(String[] args) {
        CircusCharlie game = new CircusCharlie();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public CircusCharlie() {
        // call game constructor
        super("AppCamaracharlie ", 800, 600);
    }

    public void gameStartup() {
        Log.info(getClass().getSimpleName(), "Starting up game");
        if(level1){} //Aca va lo del nivel 1
        if(level2){} //Aca va lo del nivel 2
        if(level3){} //Aca va lo del nivel 3
        try{        
            FXPlayer.init();
            FXPlayer.volume = FXPlayer.Volume.LOW;
            //FXPlayer.EVENTO1.loop();

            Mundo m=Mundo.getInstance();

            charlie=new Charlie("imagenes/JuegoCircusCharlie/Generales/charlie.png");
            charlie.setPISO(412);
            charlie.setPosition(174,charlie.getPISO());
            leon=new Charlie("imagenes/JuegoCircusCharlie/ImagenNivel1/leon.png");
            leon.setPISO(477);
            leon.setPosition(143, leon.getPISO());
            
            cam =new Camara(0,0);

            cam.setRegionVisible(getWidth(),480);

            //fondo=new Fondo("imagenes/background.png");
            fondo=new Fondo("imagenes/JuegoCircusCharlie/ImagenNivel1/FONDO.png");
            m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());
            charlie.quieto();
            leon.quietoLeon();
            //Crear los aros
            crearAros();
            //Crear los calderos
            crearCalderos();

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
                leon.leftLeon();
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)){
            if(leon.getX()+leon.getWidth()<fondo.getWidth()){
                charlie.right();
                leon.rightLeon();
            }
        }

        // check the list of key events for a pressed escape key
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getID() == KeyEvent.KEY_RELEASED)){
                charlie.quieto();
                leon.quietoLeon();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                charlie.jump();
                leon.jumpLeon();
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
        //Movimiento de los aros
        for (Aro aro : listaDeArosIzquierdo) {
            aro.update(delta);
            aro.setPosition(aro.getAroPosX() - 0.6, 217);
        }
        for (Aro aro : listaDeArosDerecho) {
            aro.update(delta);
            aro.setPosition(aro.getAroPosX() - 0.6, 217);
        }

        eliminarArosDesplazados();

        //Seccion de colisiones
        for (Aro aro : listaDeArosIzquierdo) {
            if (DetectorColiciones.detectarAro(aro, charlie)){
                System.out.println("COLISIONASTE CON UN ARO GRANDE, TENE CUIDADO");
                System.out.println("123");
                reiniciarJuegoXColisiones(charlie.getX());
            }
        }


        for(CalderoDeFuego calderito : listaDeCalderos){
            if (DetectorColiciones.detectarCalderoDeFuego(calderito, charlie)){
                System.out.println("COLISIONASTE CON UN CALDERO, TENE CUIDADO");
                System.out.println("123");
                reiniciarJuegoXColisiones(charlie.getX());
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
        //Dibujar los aros
        for (Aro aro : listaDeArosIzquierdo) {
            aro.display(g);
        }
        for (Aro aro1 : listaDeArosDerecho) {
            aro1.display1(g);
        }

        //Dibujar los calderos
        for (CalderoDeFuego calderito: listaDeCalderos) {
            calderito.display(g);
        }

        g.translate(-cam.getX(),-cam.getY());
        g.setColor(Color.red);
        g.drawString("Tecla ESC = Fin del Juego ",490,20);
    }

    // Funcion que detecta los aros y calderos que ya pasaron y los va eliminando
    public void eliminarArosDesplazados(){
    // Iterar sobre la lista original en sentido inverso para evitar problemas al eliminar elementos
        for (int i =  listaDeArosIzquierdo.size() - 1; i >= 0; i--) {
            Aro aro = listaDeArosIzquierdo.get(i);
            if (aro.getAroPosX() <= leon.getX() - 145 ) {
                listaDeArosIzquierdo.remove(i); // Eliminar el aro de la lista original
                listaDeArosDerecho.remove(i); // Eliminar el aro de la lista original
            }
        }
    }

    //Cuando detecta una colision, reiniciamos el juego en ese punto
    public void reiniciarJuegoXColisiones(double x1){
        // Busca el checkpoint más cercano a la posición x
        int[] checkpointsEjeX = {201, 990, 1814, 2654, 3451, 4259, 5066, 5869, 6668, 7433};
        int pos = 0, i, posAEnviar;
        for (i = 1; i < checkpointsEjeX.length; i++) {
            if (checkpointsEjeX[i] < x1) {
                pos = i - 1;
            }
        }
        // Reinicia el juego en el checkpoint más cercano
        int newX = checkpointsEjeX[pos]; 
        reiniciarJuego(newX);
    }
    // Método para reiniciar el juego en una posición específica
    private void reiniciarJuego(double x) {
        charlie.setPISO(412);
        charlie.setPosition(x + 31,charlie.getPISO());
        leon.setPISO(477);
        leon.setPosition(x, leon.getPISO());
    }

    //Funcion para crear aros
    public void crearAros(){
        String imagenAroGrandeIzquierda = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Izquierda.png";
        String imagenAroGrandeDerecha = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuego1Derecha.png";
        String imagenAroChicoIzquierdo = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1Izquierdo.png";
        String imagenAroChicoDerecho = "imagenes/JuegoCircusCharlie/ImagenNivel1/aroDeFuegoChico1Derecho.png";
        int posXPixel = 850;
        
        Aro primerAroIzquierda = new Aro(imagenAroGrandeIzquierda, true);
        primerAroIzquierda.setPosition(posXPixel, 217);
        listaDeArosIzquierdo.add(primerAroIzquierda);

        Aro primerAroDerecha = new Aro(imagenAroGrandeDerecha, true);
        primerAroDerecha.setPosition(posXPixel, 217);
        listaDeArosDerecho.add(primerAroDerecha);
        for (int i = 0; i < 20; i++){
            // Generar un número aleatorio entre 2 y 5
            int numeroAleatorio2 = 2 + (int)(Math.random() * ((5 - 2) + 1)); 
            for (int j = 0; j < numeroAleatorio2; j ++){
                // Generar un número aleatorio entre 350 y 600 para los pixeles
                int numeroAleatorio1 = 350 + (int)(Math.random() * ((600 - 350) + 1));
                posXPixel += numeroAleatorio1;
                Aro aroGrandeIzquierda = new Aro(imagenAroGrandeIzquierda, true);
                aroGrandeIzquierda.setPosition(posXPixel, 217);
                listaDeArosIzquierdo.add(aroGrandeIzquierda);

                Aro aroGrandeDerecha = new Aro(imagenAroGrandeDerecha, true);
                aroGrandeDerecha.setPosition(posXPixel, 217);
                listaDeArosDerecho.add(aroGrandeDerecha);
            }
            int numeroAleatorio1 = 250 + (int)(Math.random() * ((400 - 250) + 1));
            posXPixel += numeroAleatorio1;

            Aro aroChicoIzquierdo = new Aro(imagenAroChicoIzquierdo, false);
            aroChicoIzquierdo.setPosition(posXPixel, 217);
            listaDeArosIzquierdo.add(aroChicoIzquierdo);

            Aro aroChicoDerecho = new Aro(imagenAroChicoDerecho, false);
            aroChicoDerecho.setPosition(posXPixel, 217);
            listaDeArosDerecho.add(aroChicoDerecho);
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