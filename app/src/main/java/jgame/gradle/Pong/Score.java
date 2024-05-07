package jgame.gradle.Pong;

import java.awt.*;

public class Score extends Rectangle {

    private static int GAME_WIDTH;
    private static int GAME_HEIGTH;
    // private int player1;
    // private int player2;

    private int player;
    private int puntos;
    private int xPosicion;

    public Score(int GAME_WIDTH, int GAME_HEIGHT, int player, int xPosicion) {
        Score.GAME_HEIGTH = GAME_HEIGHT;
        Score.GAME_WIDTH = GAME_WIDTH;
        this.player = player;
        this.xPosicion = xPosicion;

    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGTH);

        g.drawString(String.valueOf(puntos / 10) + String.valueOf(puntos % 10), xPosicion, 50);
    }

    public int getPlayer() {
        return this.player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void mostrarMensaje(Graphics g, String ganador) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics = g.getFontMetrics();
        int x = (GamePanel.GAME_WIDTH - metrics.stringWidth(ganador)) / 2;
        int y = GamePanel.GAME_HEIGHT / 2;
        g.drawString(ganador, x, y);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics2 = g.getFontMetrics();
        int xx = (GamePanel.GAME_WIDTH - metrics2.stringWidth("Para reiniciar presione Enter")) / 2;
        int yy = GamePanel.GAME_HEIGHT / 2;
        g.drawString("Para reiniciar presione Enter", xx, yy + 30);
    }


    // lo viejo
    // public void setPlayer1(int punto){
    // player1 = punto;
    // }

    // public void setPlayer2(int punto){
    // player2 = punto;
    // }

    // public int getPlayer1(){
    // return player1;
    // }
    // public int getPlayer2(){
    // return player2;
    // }
}
