package jgame.gradle.CircusCharlie.ObjetosGraficos.Niveles;

import jgame.gradle.CircusCharlie.CircusCharlie;

public abstract class Nivel{
    public abstract void dibujar(CircusCharlie charlie);
    public abstract void actualizar(CircusCharlie charlie);
    //public abstract void reiniciarJuegoXColisiones();
}