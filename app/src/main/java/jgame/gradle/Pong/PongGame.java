package jgame.gradle.Pong;

import javax.swing.ImageIcon;

import jgame.Lanzador.Juego;

public class PongGame extends Juego {
    // public static void main(String[] args) {
    //     new PantallaInicio();
    // }

    public PongGame(){
        setNombre("Pong");
        setVersion("1.0");
        setDescripcion("Juego de tenis 2D");
        setDesarrolladores("PDF, CJ y SF");
        setImagenPortada(new ImageIcon(this.getClass().getResource("/imagenes/JuegoPong/Pong.png")));
        setImplementado(false);
    }

    @Override
    public void run() {
        new PantallaInicio();
    }

    public static void main(String[] args) {
        new PantallaInicio();
    }
}
