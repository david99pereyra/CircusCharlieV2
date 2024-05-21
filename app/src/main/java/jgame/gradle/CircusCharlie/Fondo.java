package jgame.gradle.CircusCharlie;
public class Fondo extends ObjetoGrafico {
	public Fondo(String filename) {
		super(filename);
		setPosition(0,31); // El fondo es una imagen estatica, pero muy grande
	}
}