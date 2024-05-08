package jgame.gradle.CircusCharlie;

import javax.swing.ImageIcon;

import jgame.Lanzador.Juego;

public class JuegoCircusCharlie extends Juego{

    public JuegoCircusCharlie(){
        setNombre("Circus Charlie");
        setVersion("1.0");
        setDescripcion("Juego de un Payaso y su circo");
        setDesarrolladores("PDF, CJ y SF");
        setImagenPortada(new ImageIcon(this.getClass().getResource("/imagenes/JuegoCircusCharlie/CircusCharlie.png")));
        setImplementado(true);
    }

    // @Override
    // public void run(double fps) {
    // }

    @Override
    public void run() {
        CircusCharlie game = new CircusCharlie();
        Thread t = new Thread(){
            public void run(){
                game.run(1.0 / 60.0);
            }
        };
        t.start();
        //System.exit(0); 
    }
    
}