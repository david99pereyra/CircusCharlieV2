package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;

import java.awt.GradientPaint;
import java.awt.Graphics2D;

import jgame.gradle.CircusCharlie.CircusCharlie;

public abstract class Nivel{
    protected CircusCharlie circusCharlie;
    public Nivel(CircusCharlie cc){
        this.circusCharlie = cc;
    }
    public abstract void gameDraw(Graphics2D g);
    public abstract void gameUpdate(double delta);
}