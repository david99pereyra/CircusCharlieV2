/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jgame.gradle.CircusCharlie;

import com.entropyinteractive.*; //jgame

import jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles.Nivel;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles.Nivel1;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles.Nivel2;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles.Nivel3;

import java.awt.*;
import java.awt.event.*; //eventos
import java.util.*;
import java.text.*;

public class CircusCharlie extends JGame {
    private boolean level1 = true, level2 = false, level3 = false, inicioNivel = false;
    // private static boolean level1 = false, level2 = true, level3 =false;
    Date dInit = new Date(), dAhora;
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");
    Camara cam;
    Fondo fondo;
    Charlie charlie;
    Nivel nivelactual;
    // Variables del level 1
    Nivel1 nivel1;
    Charlie leon;
    // Variables del level 2
    Nivel2 nivel2;
    // Variables del level 3
    Nivel3 nivel3;

    public static void main(String[] args) {
        CircusCharlie game = new CircusCharlie();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public CircusCharlie() {
        // call game constructor
        super("AppCamaracharlie ", 800, 600);
        // nivelactual = new Nivel1(charlie, leon, fondo);
    }

    public void gameStartup() {
        Log.info(getClass().getSimpleName(), "Starting up game");
        Mundo m = Mundo.getInstance();
        try {
            if (level1) {
                charlie = new Charlie("imagenes/JuegoCircusCharlie/Generales/charlie.png");
                leon = new Charlie("imagenes/JuegoCircusCharlie/ImagenNivel1/leon.png");
                nivel1 = new Nivel1(charlie, leon, fondo);
                cam = new Camara(0, 0);
                fondo = new Fondo("imagenes/JuegoCircusCharlie/ImagenNivel1/FONDO.png");

            } // Aca va lo del nivel 1

            if (level2) { // Aca va lo del nivel 2
                charlie.setImagen("imagenes/JuegoCircusCharlie/ImagenNivel2/charlieSoga1.png");
                nivel2 = new Nivel2(charlie, fondo);
                charlie.setPISO(220);
                charlie.setPosition(174, charlie.getPISO());
                charlie.cambioImagen1();
                cam = new Camara(0, -26);
                fondo = new Fondo("imagenes/JuegoCircusCharlie/ImagenNivel2/FONDO_Nivel2.png");
                charlie.quieto();
            }

            if (level3) {// Aca va lo del nivel 3
                nivel3 = new Nivel3(charlie, fondo);
                charlie.setPISO(430);
                charlie.setPosition(174, charlie.getPISO());
                cam = new Camara(0, 0);
                fondo = new Fondo("imagenes/JuegoCircusCharlie/ImagenNivel3/FONDO_Nivel3.png");
                charlie.quieto();
            }
            cam.setRegionVisible(getWidth(), 480);
            m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());
        } catch (Exception ex) {
            System.out.println("ERROR en gameStartup");
            ex.printStackTrace();
        }
    }

    // public void setNivel(Nivel state){
    // nivelactual = state;
    // }

    // public void dibujar(){
    // nivelactual.dibujar(this);
    // }

    // public void actualizar(){
    // nivelactual.actualizar(this);
    // }

    public void gameUpdate(double delta) {
        if (level1) { // Aca va lo del nivel 1
            Keyboard keyboard = getKeyboard();
            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT) && !nivel1.colisiono()) {
                if (leon.getX() > 10 && Nivel1.llegoAMeta() == false) {
                    charlie.left();
                    leon.leftLeon();
                }
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && !nivel1.colisiono()) {
                if (leon.getX() + leon.getWidth() < fondo.getWidth() && Nivel1.llegoAMeta() == false) {
                    charlie.right();
                    leon.rightLeon();
                }
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_Z)) {
                charlie.setPosition(7400 + 174, charlie.getY());
                leon.setPosition(7400 + 143, leon.getY());
            }
            // check the list of key events for a pressed escape key
            LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_RELEASED)) {
                    charlie.quieto();
                    leon.quietoLeon();
                }
                if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                        (event.getKeyCode() == KeyEvent.VK_SPACE) && !nivel1.colisiono()) {
                    if (Nivel1.llegoAMeta() == false) {
                        charlie.jump();
                        leon.jumpLeon();
                        FXPlayer.FX00.play();
                    }
                }
                if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                        (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    FXPlayer.EVENTO1.stop();
                    stop();
                }
            }
            leon.update(delta);
            charlie.update(delta);
            cam.seguirPersonaje(charlie); /// la camara sigue al Personaje
            cam.seguirPersonaje(leon);
            nivel1.actualizar(delta, charlie, leon);
        }

        if (level2) { // Aca va lo del nivel 2
            Keyboard keyboard = getKeyboard();
            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                if (charlie.getX() > 10 && Nivel2.llegoAMeta() == false) {
                    charlie.left();
                }
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                if (charlie.getX() + charlie.getWidth() < fondo.getWidth() && Nivel2.llegoAMeta() == false) {
                    charlie.right();
                }
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_Z)) {
                charlie.setPosition(5000 + 174, charlie.getY());
                leon.setPosition(5000 + 143, leon.getY());
            }
            // check the list of key events for a pressed escape key
            LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_RELEASED)) {
                    charlie.quieto();
                }
                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                    if (Nivel2.llegoAMeta() == false) {
                        charlie.jump();
                        FXPlayer.FX00.play();
                    }
                }
                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    FXPlayer.EVENTO1.stop();
                    stop();
                }

            }
            nivel2.actualizar(delta, charlie);
            charlie.update(delta);
            cam.seguirPersonaje(charlie); /// la camara sigue al Personaje
        }

        if (level3) { // Aca va lo del nivel 3
            Keyboard keyboard = getKeyboard();
            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                if (charlie.getX() > 10 && Nivel3.llegoAMeta() == false) {
                    charlie.left();
                }
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                if (charlie.getX() + charlie.getWidth() < fondo.getWidth() && Nivel3.llegoAMeta() == false) {
                    charlie.right();
                }
            }
            // check the list of key events for a pressed escape key
            LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
            for (KeyEvent event : keyEvents) {
                if ((event.getID() == KeyEvent.KEY_RELEASED)) {
                    charlie.quieto();
                }
                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                    if (Nivel3.llegoAMeta() == false) {
                        charlie.jump();
                        FXPlayer.FX00.play();
                    }
                }
                if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    FXPlayer.EVENTO1.stop();
                    stop();
                }
            }
            nivel3.actualizar(delta, charlie);
            charlie.update(delta);
            cam.seguirPersonaje(charlie); /// la camara sigue al Personaje
        }
        // charlie.applyForce(gravedad);
    }

    public void gameDraw(Graphics2D g) {
        if (level1) {
        } // Aca va lo del nivel 1
        if (level2) {
        } // Aca va lo del nivel 2
        if (level3) {
        } // Aca va lo del nivel 3
        if (!inicioNivel) {
            charlie.nivel(1);
            charlie.imagenNivel();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    inicioNivel = true;
                }
            }, 3000);

        } else {

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Mundo m = Mundo.getInstance();

            g.translate(cam.getX(), cam.getY());

            fondo.display(g);
            m.display(g);

            nivel1.dibujar(g, charlie, leon);
            // nivel2.dibujar(g, charlie);

            // nivel3.dibujar(g, charlie);

            g.translate(-cam.getX(), -cam.getY());
            charlie.displayScore(g);
            g.setColor(Color.red);
            g.drawString("Tecla ESC = Fin del Juego ", 490, 20);

        }
    }

    public void gameShutdown() {
        // Log.info(getClass().getSimpleName(), "Shutting down game");
    }
}