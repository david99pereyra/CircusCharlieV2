package jgame.gradle.Pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;





public class GamePanel extends JPanel implements Runnable {

    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    public static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    public static final int BALL_DIAMETER = 20;
    public static final int PADDLE_WIDTH = 25;
    public static final int PADDLE_HEIGTH = 100;
    private Thread gameThread;
    private Image image;
    private Graphics graphics;
    private Random random;

    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;

    private Score scoreJ1;
    private Score scoreJ2;

    private int puntoJ1 = 1;
    private int puntoJ2 = 1;
    private boolean enPausa = false;
    private boolean finJuego = false;

    GamePanel() {
        newPaddles();
        newBall();
        // score = new Score(GAME_WIDTH, GAME_HEIGHT);
        scoreJ1 = new Score(GAME_WIDTH, GAME_HEIGHT, 1, (GAME_WIDTH / 2) - 85);
        scoreJ2 = new Score(GAME_WIDTH, GAME_HEIGHT, 2, (GAME_WIDTH / 2) + 20);

        this.setFocusable(true);
        this.addKeyListener(new Al());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
                BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGTH / 2), PADDLE_WIDTH, PADDLE_HEIGTH, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGTH / 2), PADDLE_WIDTH,
                PADDLE_HEIGTH, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

        if (enPausa) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            FontMetrics metrics = g.getFontMetrics();
            int x = (GAME_WIDTH - metrics.stringWidth("Pausa")) / 2;
            int y = GAME_HEIGHT / 2;
            g.drawString("Pausa", x, y);
        } else {
            if (scoreJ1.getPuntos() >= 1) {
                scoreJ1.mostrarMensaje(g, "Gana Jugador 1");
                finJuego = !finJuego;
            } else if (scoreJ2.getPuntos() >= 1) {
                scoreJ2.mostrarMensaje(g, "Gana Jugador 2");
                finJuego = !finJuego;
            }
        }

    }

    public void draw(Graphics g) {

        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        scoreJ1.draw(g);
        scoreJ2.draw(g);
    }

    public void move() {

        paddle1.move();
        paddle2.move();
        ball.move();

    }

    public void checkCollision() {

        // bounce ball off top & buttom windos edges
        if (ball.y <= 0) {
            ball.setYDirection(-ball.getYVelocity());
            Sonido.iniciar("wall");
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.getYVelocity());
            Sonido.iniciar("wall");
        }

        // bounses ball off paddles
        if (ball.intersects(paddle1)) {

            ball.setXVelocity(Math.abs(ball.getXVelocity()));

            // optional for more difficulty
            ball.setXVelocity(ball.getXVelocity() + 1);
            if (ball.getYVelocity() > 0) {
                ball.setYVelocity(ball.getYVelocity() + 1);
            } else {
                ball.setYVelocity(ball.getYVelocity() - 1);
            }
            ball.setYDirection(ball.getYVelocity());
            ball.setXDirection(ball.getXVelocity());
            Sonido.iniciar("Paddle");
        }

        if (ball.intersects(paddle2)) {
            ball.setXVelocity(Math.abs(ball.getXVelocity()));

            // optional for more difficulty
            ball.setXVelocity(ball.getXVelocity() + 1);
            if (ball.getYVelocity() > 0) {
                ball.setYVelocity(ball.getYVelocity() + 1);
            } else {
                ball.setYVelocity(ball.getYVelocity() - 1);
            }
            ball.setYDirection(ball.getYVelocity());
            ball.setXDirection(-ball.getXVelocity());
            Sonido.iniciar("Paddle");
        }

        // Stop the paddles at window edges
        // Paddle1
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGTH)) {
            paddle1.y = (GAME_HEIGHT - PADDLE_HEIGTH);
        }

        // Paddle2
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGTH)) {
            paddle2.y = (GAME_HEIGHT - PADDLE_HEIGTH);
        }

        // Give a player a 1 point and create new paddles and ball
        if (ball.x <= 0) {
            Sonido.iniciar("point");
            scoreJ1.setPuntos(puntoJ1++);
            newPaddles();
            newBall();
            System.out.println("Player 2: " + scoreJ1.getPuntos());
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            Sonido.iniciar("point");
            scoreJ2.setPuntos(puntoJ2++);
            newPaddles();
            newBall();
            System.out.println("Player 1: " + scoreJ2.getPuntos());
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (!finJuego) {

            if (!enPausa) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                if (delta >= 1) {
                    move();
                    checkCollision();
                    delta--;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();

        }
    }

    private void cerrarVentana(){
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose(); // Cerrar la ventana actual
        }
    }
    private class Al extends KeyAdapter { 

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_P) {
                enPausa = !enPausa;
            }
            if (!enPausa) {
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                cerrarVentana();
                new PantallaInicio();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

    }
}
