package jgame.gradle.Pong;
import jgame.gradle.CircusCharlie.ObjetosGraficos.Obstaculos.*;
import jgame.gradle.CircusCharlie.*;
import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Log;

import java.awt.event.*; //eventos
import java.util.*;
import java.text.*;
import java.awt.*;

public class Pong extends JGame{
    Date dInit = new Date();
    Date dAhora;
    SimpleDateFormat ft = new SimpleDateFormat("mm:ss");
    private Fondo fondo;
    private Raqueta raquetazo1;
    private Raqueta raquetazo2;
    private Ball ball;
    private Score scoreJ1;
    private Score scoreJ2;
    private boolean enPausa = false;
    private boolean finJuego = false;
    private boolean pPresionado = false;
    private boolean enterPresionado = false;

    public static void main(String[] args) {
        Pong game = new Pong();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public Pong() {
        super("Pong", 800, 600);
    }

    public void gameStartup() {
        Log.info(getClass().getSimpleName(), "Starting up game");
        fondo = new Fondo(800, 600);
        newRaquetas();

        newBall();

        scoreJ1 = new Score(1, ((int)fondo.getWidth() / 2) - 85 );
        scoreJ2 = new Score(2, ((int)fondo.getWidth() / 2) + 20);
    }

    public void gameUpdate(double delta) {
        if(!finJuego){     
            Keyboard keyboard = getKeyboard();   
            // Verificar si se presiona 'P' para pausar/reanudar el juego
            if (keyboard.isKeyPressed(KeyEvent.VK_P)) {
                if(!pPresionado && !finJuego){
                    enPausa = !enPausa;
                    pPresionado = true;
                }
            }else{
                pPresionado = false;
            }

            // Verificar si se presiona 'Enter' para reiniciar el juego
            if (keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
                if(!enterPresionado && finJuego){
                    reiniciarJuego();
                    enterPresionado = true;
                }
            }else{
                enterPresionado = false;
            }
            if(!enPausa){
                // Funcion movimiento de las paletas
                movimientoTeclado(keyboard);
        
                // Check the list of key events for a pressed escape key
                LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
                for (KeyEvent event : keyEvents) {
                    if ((event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                        stop();
                    }
                }
                // Mover la pelota
                ball.mover();
                // Colisiones
                // Colisión de la pelota con los bordes
                DetectorColiciones.colisionPelotaContraBordesSupInf(ball, fondo);
                // Colisión de la pelota con las raquetas
                DetectorColiciones.colisionPelotaRaqueta(ball, raquetazo1);
                DetectorColiciones.colisionPelotaRaqueta(ball, raquetazo2);
                // Colsiion de la pelota en los laterales
                colisionLateral(ball);
                
                // Limitar movimiento de las raquetas
                limitesPaletas(raquetazo1, raquetazo2);
            }
        } else{
            Keyboard keyboard = getKeyboard();
            if(keyboard.isKeyPressed(KeyEvent.VK_ENTER)){
                reiniciarJuego();
            }
        }
    }

    public void gameDraw(Graphics2D g) {
        fondo.display(g);
        // Dibujar las raquetas
        raquetazo1.display(g);
        raquetazo2.display(g);

        // Dibujar la pelota solo si el juego no ha terminado
        if (!finJuego) {
            g.setColor(Color.WHITE);
            g.fill(ball.getGrafico());
        }
        // Dibujar puntaje
        scoreJ1.draw(g, fondo);
        scoreJ2.draw(g, fondo);
        if (enPausa) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            FontMetrics metrics = g.getFontMetrics();
            int x = ((int) fondo.getWidth() - metrics.stringWidth("Pausa")) / 2;
            int y = (int) fondo.getHeight() / 2;
            g.drawString("Pausa", x, y);
        } else if (finJuego) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            FontMetrics metrics = g.getFontMetrics();
            String mensajeFin = scoreJ1.getPuntos() >= 3 ? "Gana Jugador 1" : "Gana Jugador 2";
            int x = ((int) fondo.getWidth() - metrics.stringWidth(mensajeFin)) / 2;
            int y = (int) fondo.getHeight() / 2;
            g.drawString(mensajeFin, x, y);
            String reiniciarMensaje = "Presione Enter para reiniciar";
            x = ((int) fondo.getWidth() - metrics.stringWidth(reiniciarMensaje)) / 2;
            y += 50;
            g.drawString(reiniciarMensaje, x, y);
        }
    }
    public void newRaquetas(){
        raquetazo1 = new Raqueta(13, 280, 1);
        raquetazo2 = new Raqueta(790, 280, 2);
    }

    public void newBall(){
        ball = new Ball(400, 300);
    }

    public void gameShutdown() {
    }

    public void reiniciarJuego(){
        newRaquetas();
        scoreJ1.setPuntos(0);
        scoreJ2.setPuntos(0);
        finJuego = false;
        enPausa = false;
    }
    
    public void colisionLateral(Ball ball){
        if(ball.getX() <= 0){
            scoreJ1.setPuntos(scoreJ1.getPuntos() + 1);
            newBall();
            newRaquetas();
            try {
                Thread.sleep(80);  // Duerme el hilo durante 1000 milisegundos (1 segundo)
            } catch (InterruptedException e) {
                // Maneja la excepción aquí, por ejemplo, imprimiendo un mensaje de error
                e.printStackTrace();
            }
        }
        if(DetectorColiciones.colisionPelotaContraLateralDerecha(ball, (int)fondo.getWidth())){
            scoreJ2.setPuntos(scoreJ2.getPuntos() + 1);
            newBall();
            newRaquetas();
            try {
                Thread.sleep(80);  // Duerme el hilo durante 1000 milisegundos (1 segundo)
            } catch (InterruptedException e) {
                // Maneja la excepción aquí, por ejemplo, imprimiendo un mensaje de error
                e.printStackTrace();
            }
        }  
        if(scoreJ1.getPuntos() == 3 || scoreJ2.getPuntos() == 3){
            finJuego = true;
        }      
    }
    
    public void limitesPaletas(Raqueta raquetazo1, Raqueta raquetazo2){
        final int PADDING_TOP = 32;
        final int PADDING_BOTTOM = 0;
        if (raquetazo1.getY() < PADDING_TOP) {
            raquetazo1.setY(PADDING_TOP);
        }
        if (raquetazo1.getY() + raquetazo1.getHeight() > fondo.getHeight() - PADDING_BOTTOM) {
            raquetazo1.setY(fondo.getHeight() - PADDING_BOTTOM - raquetazo1.getHeight());
        }
        if (raquetazo2.getY() < PADDING_TOP) {
            raquetazo2.setY(PADDING_TOP);
        }
        if (raquetazo2.getY() + raquetazo2.getHeight() > fondo.getHeight() - PADDING_BOTTOM) {
            raquetazo2.setY(fondo.getHeight() - PADDING_BOTTOM - raquetazo2.getHeight());
        }
    }

    public void movimientoTeclado(Keyboard keyboard){
        
        if(keyboard.isKeyPressed(KeyEvent.VK_UP)){
            raquetazo1.up();
        }
        if(keyboard.isKeyPressed(KeyEvent.VK_DOWN)){
            raquetazo1.down();
        }
        if(keyboard.isKeyPressed(KeyEvent.VK_W)){
            raquetazo2.up();
        }
        if(keyboard.isKeyPressed(KeyEvent.VK_S)){
            raquetazo2.down();
        }
    }
}
