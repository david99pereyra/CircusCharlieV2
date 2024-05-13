package jgame.gradle.CircusCharlie;

import java.awt.image.*;
import java.util.Timer;
import java.util.TimerTask;

import jgame.gradle.FontManager;

import java.awt.*;

public class Score extends ObjetoGrafico {

    private int bonus = 5000;
    private int score = 0;
    private int hiScore = 0;
    private int state = 0;

    private boolean descuentoBonusActivo = true;

    public Score() {
        this.setPosition(10, 10);
        iniciarTemporizador();
    }

    public void update() {
        FontManager.getInstance();

        BufferedImage img = new BufferedImage(800, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Pixel Emulator", Font.BOLD, 20));

        String scoreText = String.format("%06d", score);
        g.drawString(scoreText, 200, 55);

        String HIText = "HI-" + String.format("%06d", hiScore);
        int HITextWidth = g.getFontMetrics().stringWidth(HIText);
        int HITextX = (800 - HITextWidth) / 2;
        g.drawString(HIText, HITextX, 55);

        String StateText = "State-" + state;
        g.drawString(StateText, 500, 55);

        String bonusText = "BONUS-" + (int) Math.max(bonus, 0);
        int bonusTextWidth = g.getFontMetrics().stringWidth(bonusText);
        int bonusTextX = (800 - bonusTextWidth) / 2;
        g.drawString(bonusText, bonusTextX, 80);

        g.drawRect(100, 30, 600, 60);

        this.setImagen(img);

    }

    public void detenerDescuentoBonus() {
        descuentoBonusActivo = false;
        sumarRestoBonusAScore();
    }

    private synchronized void sumarRestoBonusAScore() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                while (bonus > 0) {
                    score++;
                    bonus--;
                    update();
                }
                
            }
        }, 1000,6000);
         // Actualizar la representación visual
    }
    

    private void iniciarTemporizador() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(descuentoBonusActivo){
                    disminuirBonus();
                }
            }
        }, 1000, 300); // Inicia el temporizador después de 200 ms, y luego lo repite cada 200 ms
    }

    private synchronized void disminuirBonus() {
        if (bonus > 0) {
            bonus -= 10;
            if (bonus < 0) {
                bonus = 0;
            }
            update();
        }
    }

    public void nivelActual(int nivel) {
        this.state += nivel;
    }

    public void sumarScore(int valor) {
        this.score += valor;
    }

    public void scorePred(){
        this.score = 5000;
    }


}
