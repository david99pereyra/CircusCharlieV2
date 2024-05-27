package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;

import java.awt.GradientPaint;
import java.awt.Graphics2D;

import jgame.gradle.CircusCharlie.*;

public abstract class Nivel{
    protected CircusCharlie circusCharlie;
    Camara cam;
    Fondo fondo;
    public static boolean colisiono = false, llegoAMeta = false, mostrarNivel = false, accion = false, restar = false;
    public Nivel(CircusCharlie cc){
        this.circusCharlie = cc;
    }
    public abstract void gameDraw(Graphics2D g);
    public abstract void gameUpdate(double delta);
    public abstract boolean colisiono();
}