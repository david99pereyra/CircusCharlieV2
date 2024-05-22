package jgame.gradle.Pong;

import java.awt.Rectangle;

public class DetectorColisiones extends Rectangle{
    public static void colisionPelotaContraBordesSupInf(Ball pelotita, Fondo fondito){
        // Colisión de la pelota con los bordes
        if (pelotita.getX() < 0 || pelotita.getX() + pelotita.getRadio() * 2 > fondito.getWidth()) {
            pelotita.rebotarHorizontal();
        }
        if (pelotita.getY() < 30 || pelotita.getY() + pelotita.getRadio() * 2 > fondito.getHeight()) {
            pelotita.rebotarVertical();
        }
    }
    public static void colisionPelotaRaqueta(Ball pelotita, Raqueta raquetazo){
        Rectangle raquetazoBounds = new Rectangle((int) raquetazo.getX(), (int) raquetazo.getY(), raquetazo.getWidth(), raquetazo.getHeight());
        Rectangle pelotitaBounds = new Rectangle((int) pelotita.getX(), (int) pelotita.getY(), pelotita.getRadio() * 2, pelotita.getRadio() * 2);
        if (raquetazoBounds.intersects(pelotitaBounds)) {
            pelotita.rebotarHorizontal();
        }   
    }

    public static boolean colisionPelotaContraLateralIzquierda(Ball pelotita){
        boolean band = false;
        if(pelotita.getX() <= 0){
            band = true;
        }
        return band;
    }

    public static boolean colisionPelotaContraLateralDerecha(Ball pelotita, int tamañoFondo){
        boolean band = false;
        if(pelotita.getX() >= tamañoFondo - 15){// - pelotita.getWidth()){
            band = true;
        }
        return band;
    }
}
