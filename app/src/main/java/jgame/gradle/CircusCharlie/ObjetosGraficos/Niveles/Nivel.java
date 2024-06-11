package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;
import jgame.gradle.CircusCharlie.*;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.Pelota;
import java.awt.Graphics2D;
import java.util.*;
import com.entropyinteractive.Keyboard;
import java.awt.event.KeyEvent;

public abstract class Nivel{
    protected CircusCharlie circusCharlie;
    Camara cam;
    Fondo fondo;
    protected static boolean colisiono = false;
    protected static boolean llegoAMeta = false;
    protected static boolean mostrarNivel = false;
    protected static boolean accion = false;
    protected static boolean restar = false;
    protected boolean accionEjecutar;
    protected Timer temporizador = new Timer();
    Date dInit = new Date();
    Date dReloj;
    Date dAhora;
    
    public Nivel(CircusCharlie cc){
        this.circusCharlie = cc;
    }

    public abstract void gameDraw(Graphics2D g);
    public abstract void gameUpdate(double delta, Keyboard keyboard);
    public abstract boolean colisiono();

    public void movimientoTeclas(double delta, Keyboard keyboard){
        if(circusCharlie.getNivelActual() instanceof Nivel3){
            Pelota pelotaActual = Nivel3.getPelotaEnLaQueEstaParadoCharlie(circusCharlie.getCharlie());
            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                if(circusCharlie.getCharlie().getEnLaPelota() && pelotaActual != null){
                    pelotaActual.left();
                    pelotaActual.update(delta);
                }
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                if(circusCharlie.getCharlie().getEnLaPelota() && pelotaActual != null){
                    pelotaActual.right();
                    pelotaActual.update(delta);
                }
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            if (circusCharlie.getCharlie().getX() > 10){
                circusCharlie.getCharlie().left();
                circusCharlie.getCharlie().cambioImagen1();
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if ((circusCharlie.getCharlie().getX() + circusCharlie.getCharlie().getWidth() < fondo.getWidth() && !Nivel2.llegoAMeta()) || (circusCharlie.getCharlie().getX() + circusCharlie.getCharlie().getWidth() < fondo.getWidth() && !Nivel3.llegoAMeta())) {
                circusCharlie.getCharlie().right();
                circusCharlie.getCharlie().cambioImagen1();
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_Z)) {
            circusCharlie.getCharlie().setPosition(5000 + 174, circusCharlie.getCharlie().getY());
        }
        // check the list of key events for a pressed escape key
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_RELEASED)) {
                circusCharlie.getCharlie().quieto();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                if (Nivel3.llegoAMeta() == false || Nivel2.llegoAMeta() == false) {
                    circusCharlie.getCharlie().jump();
                    FXPlayer.FX00.play();
                }
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                FXPlayer.EVENTO1.stop();
                circusCharlie.stop();
            }
            
        }
        circusCharlie.getCharlie().update(delta);
        cam.seguirPersonaje(circusCharlie.getCharlie(),180); /// la camara sigue al Personaje
    }

    public void movimientoTeclas(double delta, Keyboard keyboard, Leon leon){
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            if (leon.getX() > 10 && Nivel1.llegoAMeta() == false) {
                circusCharlie.getCharlie().left();
                leon.left();
                if(!circusCharlie.getCharlie().saltando()){
                    circusCharlie.getCharlie().cambioImagen();
                }
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (leon.getX() + leon.getWidth() < fondo.getWidth() && Nivel1.llegoAMeta() == false) {
                circusCharlie.getCharlie().right();
                leon.right();
                if(!circusCharlie.getCharlie().saltando()){
                    circusCharlie.getCharlie().cambioImagen();
                }
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_Z)) {
            circusCharlie.getCharlie().setPosition(7400 + 174, circusCharlie.getCharlie().getY());
            leon.setPosition(7400 + 143, leon.getY());
        }
        // check the list of key events for a pressed escape key
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_RELEASED)) {
                circusCharlie.getCharlie().quieto();
                leon.quieto();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_SPACE)) {
                if (Nivel1.llegoAMeta() == false) {
                    circusCharlie.getCharlie().jump();
                    leon.jump();
                    FXPlayer.FX00.play();
                }
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                FXPlayer.EVENTO1.stop();
                circusCharlie.stop();
            }
        }
        leon.update(delta);
        circusCharlie.getCharlie().update(delta);
        cam.seguirPersonaje(circusCharlie.getCharlie(), leon, 143); /// la camara sigue al Leon
    }

    public void animacionMeta(double delta){
        if (Nivel1.llegoAMeta() || Nivel2.llegoAMeta() || Nivel3.llegoAMeta()) {
            if (dReloj == null) {
                dReloj = new Date();
            }
            dAhora = new Date();
            long diffSeconds = 0;
            long dateDiff = dAhora.getTime() - dReloj.getTime();
            diffSeconds = dateDiff / 1000 % 60;
            circusCharlie.getCharlie().updateLlegadaMeta(delta);
        }
    }    
}